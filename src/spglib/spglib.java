/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.1
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package spglib;

public class spglib {
  public static void spg_show_symmetry(SWIGTYPE_p_a_3__double lattice, SWIGTYPE_p_a_3__double position, SWIGTYPE_p_int types, int num_atom, double symprec) {
    spglibJNI.spg_show_symmetry(SWIGTYPE_p_a_3__double.getCPtr(lattice), SWIGTYPE_p_a_3__double.getCPtr(position), SWIGTYPE_p_int.getCPtr(types), num_atom, symprec);
  }

  public static int spg_find_primitive(SWIGTYPE_p_a_3__double lattice, SWIGTYPE_p_a_3__double position, SWIGTYPE_p_int types, int num_atom, double symprec) {
    return spglibJNI.spg_find_primitive(SWIGTYPE_p_a_3__double.getCPtr(lattice), SWIGTYPE_p_a_3__double.getCPtr(position), SWIGTYPE_p_int.getCPtr(types), num_atom, symprec);
  }

  public static int spg_get_international(String symbol, SWIGTYPE_p_a_3__double lattice, SWIGTYPE_p_a_3__double position, SWIGTYPE_p_int types, int num_atom, double symprec) {
    return spglibJNI.spg_get_international(symbol, SWIGTYPE_p_a_3__double.getCPtr(lattice), SWIGTYPE_p_a_3__double.getCPtr(position), SWIGTYPE_p_int.getCPtr(types), num_atom, symprec);
  }

  public static SWIGTYPE_p_a_3__double getVectsArray() {
    long cPtr = spglibJNI.getVectsArray();
    return (cPtr == 0) ? null : new SWIGTYPE_p_a_3__double(cPtr, false);
  }

  public static void putInDoubleNby3Array(SWIGTYPE_p_a_3__double a, int i, int j, double v) {
    spglibJNI.putInDoubleNby3Array(SWIGTYPE_p_a_3__double.getCPtr(a), i, j, v);
  }

  public static double getFromDoubleNby3Array(SWIGTYPE_p_a_3__double a, int i, int j) {
    return spglibJNI.getFromDoubleNby3Array(SWIGTYPE_p_a_3__double.getCPtr(a), i, j);
  }

  public static void deleteIntArray(SWIGTYPE_p_int t) {
    spglibJNI.deleteIntArray(SWIGTYPE_p_int.getCPtr(t));
  }

  public static void deleteDoubleNby3Array(SWIGTYPE_p_a_3__double a) {
    spglibJNI.deleteDoubleNby3Array(SWIGTYPE_p_a_3__double.getCPtr(a));
  }

  public static SWIGTYPE_p_a_3__double getPositionsArray(int size) {
    long cPtr = spglibJNI.getPositionsArray(size);
    return (cPtr == 0) ? null : new SWIGTYPE_p_a_3__double(cPtr, false);
  }

  public static SWIGTYPE_p_int getTypesArray(int size) {
    long cPtr = spglibJNI.getTypesArray(size);
    return (cPtr == 0) ? null : new SWIGTYPE_p_int(cPtr, false);
  }

  public static int getFromIntArray(SWIGTYPE_p_int a, int i) {
    return spglibJNI.getFromIntArray(SWIGTYPE_p_int.getCPtr(a), i);
  }

  public static void putInIntArray(SWIGTYPE_p_int a, int i, int v) {
    spglibJNI.putInIntArray(SWIGTYPE_p_int.getCPtr(a), i, v);
  }

}
