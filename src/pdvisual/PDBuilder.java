package pdvisual;

import Jama.Matrix;

import chemistry.*;
import crystallography.*;

import ga.GAParameters;
import ga.Organism;

import java.io.Serializable;
import java.util.*;


/**
 * PDBuilder.java
 * 
 * Takes composition/source info and creates a PDData object.
 * Plan on this being a Builder patterned class to deal with 
 * different types of data sources.
 * 
 * External interface consists of two calls:
 *  - construct a PDBuilder object - give it various info about the system
 *  - call getPDData() - to get the finished PDData object
 * Most actual computation will be done during the getPDData call.
 * 
 * @author wtipton
 *
 */
public class PDBuilder implements Serializable {
	final static long serialVersionUID = 1;

    public static double DET_TOL = 0.00001;
    /**
     * if a vertex of the convex hull lies above this number in formation energy
     * then it shouldn't be a facet ! 
     */
    public static double EFORM_TOL = 0.00001;
    protected List<IComputedEntry> entries;
    protected List<Element> elements;
    protected Map<Element, Double> chempots;

    public PDBuilder(List<IComputedEntry> _entries, List<Element> _elements, Map<Element, Double> _chempots) {
        entries = new LinkedList<IComputedEntry>(_entries);
        elements = new LinkedList<Element>(_elements);
        chempots = new HashMap<Element, Double>(_chempots);
    }
    
    public void addEntry(IComputedEntry c) {
    	entries.add(c);
    }

    public PDData getPDData() {

        PDData newPDData = new PDData(entries, elements, chempots);
        
        /*
         * qconvex doesn't like when we have a number of equals to the dimension of the convex hull space
         * Ex: when we have a binary (dim 2) with only 2 entries basically a trivially immiscible system
         * qconvex would throw something like: qhull   error: not enough points (2) to construct initial simplex (need 3)
         * 
         * In this case we just return directly the PDData and we don't compute the convexhull
         * 
         * Geoffroy, 21 october 2008
         */

        if (entries.size() == elements.size()) {
            List<List<Integer>> facets = new LinkedList<List<Integer>>();
            List<Integer> list = new LinkedList<Integer>();
            for (int i = 0; i < entries.size(); i++) {
                list.add(i);
            }
            facets.add(list);
            newPDData.setFacets(facets);
            return newPDData;
        }

        // find the convex hull
        PDConvexable convexableData = new PDConvexable(newPDData); 
        Convexhull chull = new Convexhull(convexableData, false);
        List<List<Integer>> facets = chull.getFaLists();
        
        // remove vertical facets:
        // note that the PDk option in qconvex is not good for this as it is susceptible
        // to precision error
        /**
         * Fri Jul 18 01:59:29 EDT 2008 Chris
         * One, of probably several ways to remove vertical facets is to determine when the
         * matrix c[i][j] == amount of i^th element in composition of j^th vertex and
         * dim(c) = numElement x numElement has a determinant of zero (i.e., 
         * it lies in a subspace of the composition space
         * Example: facets in Li-Ti-O include some which lie along the Ti-O axis
         *          and are parallel to the energy axis 
         */
        Iterator<List<Integer>> iFacet = facets.iterator();
        while (iFacet.hasNext()) {
            List<Integer> facet = iFacet.next();
            boolean needToRemove = false;
            //build array 
            double[][] C = new double[elements.size()][elements.size()];

            for (int i = 0; i < facet.size(); i++) {
                Composition ci = entries.get(facet.get(i)).getComposition();
                for (int j = 0; j < elements.size(); j++) {
                    C[j][i] = ci.getFractionalCompo(elements.get(j));
                }
            }
            Matrix m = new Matrix(C);
            if (Math.abs(m.det()) < DET_TOL) {
                needToRemove = true;
            }
            if (needToRemove) {
            	if (GAParameters.getParams().getVerbosity() >= 5) {
	                System.out.println("removing facet (all vertices on vertical face of hull):");
	                for (int i = 0; i < facet.size(); i++) {
	                    System.out.println(newPDData.getEntry(facet.get(i)));
	                }
            	}
                iFacet.remove();
            }
        }
        /**
         * one last check -- remove facets where the formation energy of
         * one of the points on the facet is positive (i.e., it is part of the
         * convex hull, but in the Eform > 0 space)
         */
        iFacet = facets.iterator();
        List<Double> eforms = newPDData.getFormEnergiesPerAtom();
        while (iFacet.hasNext()) {
            List<Integer> facet = iFacet.next();
            boolean removeFacet = false;
            for (Integer ivert : facet) {
                if (eforms.get(ivert) > EFORM_TOL) {
                    removeFacet = true;
                    break;
                }
            }
            if (removeFacet) {
            //    System.out.println("removing facet (vertex has positive eform):");
            //    for (int i = 0; i < facet.size(); i++) {
            //        System.out.println(newPDData.getEntry(facet.get(i)));
            //    }
                iFacet.remove();
            }
        }

        newPDData.setFacets(facets);

        // compute the adjacent facets
        // build the adjacency list
        List<List<Integer>> adjacencyList = new LinkedList<List<Integer>>();
        // get two facets and see if they are adjacent.  they are adjacent if they have
        // (dim - 1) vertices in common.
        for (int i = 0; i < facets.size(); i++) {
            for (int j = 0; j < facets.size(); j++) {
                if (i == j) {
                    continue;
                }
                List<Integer> fi = facets.get(i);
                List<Integer> fj = facets.get(j);
                int numVerticesInCommon = 0;
                for (int m = 0; m < fi.size(); m++) {
                    for (int n = 0; n < fj.size(); n++) {
                        if (fi.get(m) == fj.get(n)) {
                            numVerticesInCommon++;
                        }
                    }
                }
                if (numVerticesInCommon == elements.size() - 1) {
                    List<Integer> newPair = new LinkedList<Integer>();
                    newPair.add(i);
                    newPair.add(j);
                    adjacencyList.add(newPair);
                }
            }
        }
        newPDData.setAdjacencyList(adjacencyList);

        return newPDData;
    }

	public boolean containsEntry(IComputedEntry o) {
		for (IComputedEntry e : entries)
			if (o == e) // just compare references?
				return true;
		return false;
	}
}
