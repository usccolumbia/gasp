package crystallography;

import ga.GAParameters;
import ga.StructureOrg;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import utility.Constants;
import utility.Vect;
import vasp.VaspOut;

public class Isotropy {
	
	//  in case program bombs, try again w/ accuracy 0.01
	public static double accuracy1 = 0.05; 
	public static double accuracy2 = 0.01; 
	
	public static String getFindsymInput(Cell c, double accuracy) {
		StringBuilder result = new StringBuilder();
		result.append("title \n");
		result.append(accuracy + "  accuracy\n");
		result.append("1  lattice vectors given as vectors\n");
		for (int i = 0; i < Constants.numDimensions; i++) {
			for (int j = 0; j < Constants.numDimensions; j++)
				result.append(c.getLatticeVectors().get(i).getCartesianComponents().get(j) + " ");
			result.append("\n");
		}
		result.append("1  form of primitive lattice vectors\n");
		result.append("1 0 0\n");
		result.append("0 1 0\n");
		result.append("0 0 1\n");
		result.append(c.getNumSites() + "\n");
		// seems to be important that the integers specifying the types start at 1.
		// findsym crashes otherwise when trying to free some memory
		for (int i = 0; i < c.getNumSites(); i++)
			result.append(c.getComposition().getElements().indexOf(c.getSite(i).getElement())+1 + " ");
		result.append("\n");
		for (int i = 0; i < c.getNumSites(); i++) {
			for (double d : c.getSite(i).getCoords().getComponentsWRTBasis(c.getLatticeVectors()))
				result.append(d + " ");
			result.append("\n");
		}
		
		return result.toString();
	}
	
	private static String runFindsym(String input) {
		StringBuilder outBuilder = new StringBuilder();

		try {
	        String line;
	      //  String env[] = new String[1];
	    //    env[0] = "ISODATA=" + findsymDir;
	     //   Process p = Runtime.getRuntime().exec(findsymDir + "/" + findsymBin, env);
	        Process p = Runtime.getRuntime().exec("callfindsym");
	        
	        OutputStreamWriter inw = new OutputStreamWriter(p.getOutputStream());
	        BufferedWriter in = new BufferedWriter(inw);
	        try {
	        	in.write(input);
	        	in.flush();
	        } finally {
		        in.close();
		        inw.close();
	        }
	        
	        InputStreamReader outw = new InputStreamReader(p.getInputStream());
	        BufferedReader out = new BufferedReader(outw);
	        try {
		        while ((line = out.readLine()) != null) {
		        	outBuilder.append(line + "\n");
		        }
	        } finally {
		        out.close(); 
		        outw.close();
	        }
	        
	        // flush the error stream. apparently if all the streams arent flushed
	        // the java runtime can leak file descriptors and it'll run out and
	        // crash eventually
	        InputStreamReader eoutw = new InputStreamReader(p.getErrorStream());
	        BufferedReader eout = new BufferedReader(eoutw);
	        try {
		        while ((line = eout.readLine()) != null) 
		        	;
	        } finally {
		        eout.close(); 
		        eoutw.close();
	        }
	        
	      }
	      catch (Exception err) {
	        err.printStackTrace();
	      }

	      return outBuilder.toString();
	}
	
	public static String getFindsymOutput(Cell cell) {
		String output = runFindsym(getFindsymInput(cell, accuracy1));
		
		if (output.contains("bombed")) {
			output = runFindsym(getFindsymInput(cell, accuracy2));
		}
		
		return output;
	}
	
	private static Cell parseWyckoffCell(String output, Cell origCell) throws Exception {
		// get lattice parameters and site types
		boolean nextLineIsLatticeParams = false;
		String latticeParms = null;
		boolean nextLineIsTypes = false;

		List<Integer> types = new LinkedList<Integer>();
		for (String line : output.split("\n")) {
		//	System.out.print (line);
			if (nextLineIsLatticeParams)
				latticeParms = line;
			nextLineIsLatticeParams = line.startsWith("Values of a,b,c,alpha,beta,gamma");	
			if (line.startsWith("Position of each atom"))
				nextLineIsTypes = false;
			if (nextLineIsTypes) {
				String typeStrs[] = line.split("  *");
				for (int i = 1; i < typeStrs.length; i++)
					types.add(Integer.parseInt(typeStrs[i]));
			}
			if (!nextLineIsTypes)
				nextLineIsTypes = line.startsWith("Type of each atom");	
		}
		
		/*
		if (latticeParms == null) {
			System.out.println("ERROR in Isotropy.getWyckoffCell: findsym output probably malformed");
			System.out.println(cell);
			System.out.println(getFindsymInput(cell));
			System.out.println(output);
			return null;
		} */
		
		// Make the lattice parameters
		String lParms[] = latticeParms.split("  *");
		double a = Double.parseDouble(lParms[1]);
		double b = Double.parseDouble(lParms[2]);
		double c = Double.parseDouble(lParms[3]);
		double alpha = Double.parseDouble(lParms[4]) / 180 * Math.PI;
		double beta = Double.parseDouble(lParms[5]) / 180 * Math.PI;
		double gamma = Double.parseDouble(lParms[6]) / 180 * Math.PI;
		List<Vect> basis = Cell.getVectorsfromLParams(a,b,c,alpha,beta,gamma);		
		
		// get wyckoff positions
		boolean positionsStarted = false;
		List<Site> sites = new LinkedList<Site>();
		for (String line : output.split("\n")) {
			if (!positionsStarted && line.startsWith("Wyckoff position"))
				positionsStarted = true;
			if (positionsStarted && line.startsWith("------"))
				break;
			if (positionsStarted) {
				if (!line.startsWith("Wyckoff position")) {
					String sitesStrs[] = line.split("  *");
					int typeIndx = Integer.parseInt(sitesStrs[1]) - 1;
					double xf = Double.parseDouble(sitesStrs[2]);
					double yf = Double.parseDouble(sitesStrs[3]);
					double zf = Double.parseDouble(sitesStrs[4]);
					int type = types.get(typeIndx);
					sites.add(new Site(origCell.getComposition().getElements().get(type - 1), new Vect(xf,yf,zf,basis)));
				}
			}
		}
		 
		return new Cell(basis, sites);
	}
	
	public static Cell getWyckoffCell(Cell cell) {
		Cell answer = null;

		String output = getFindsymOutput(cell);
		try {
			answer = parseWyckoffCell(output,cell);
		} catch (Exception x) {
			if (GAParameters.getParams().getVerbosity() >= 4) {
				System.out.println(x.getLocalizedMessage());
				System.out.println("Warning: Exception in parsing output from findsym for both accuracies tried for cell and input:");
				System.out.println(cell);
				System.out.println(getFindsymInput(cell, accuracy2));
			}
			answer = cell;
		}
		
		return answer;
	}
	
	public static void main(String args[]) {
		//Cell c = StructureOrg.parseCif(new File("/home/wtipton/cifs/2.cif"));
		Cell c = VaspOut.getPOSCAR("/home/wtipton/cifs/POSCAR");
		
		//String fsInput = utility.Utility.readStringFromFile("/home/wtipton/fstest");
		
	//	c = VaspOut.getPOSCAR("/home/wtipton/cifs/POSCAR");
		
		//System.out.println(runFindsym(getFindsymInput(c)));		
	//	System.out.println(getFindsymOutput(c));	
		System.out.println(getWyckoffCell(c));
		//System.out.println(runFindsym(fsInput));
	}

}