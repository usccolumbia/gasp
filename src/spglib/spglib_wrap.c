/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.1
 * 
 * This file is not intended to be easily readable and contains a number of 
 * coding conventions designed to improve portability and efficiency. Do not make
 * changes to this file unless you know what you are doing--modify the SWIG 
 * interface file instead. 
 * ----------------------------------------------------------------------------- */

#define SWIGJAVA

/* -----------------------------------------------------------------------------
 *  This section contains generic SWIG labels for method/variable
 *  declarations/attributes, and other compiler dependent labels.
 * ----------------------------------------------------------------------------- */

/* template workaround for compilers that cannot correctly implement the C++ standard */
#ifndef SWIGTEMPLATEDISAMBIGUATOR
# if defined(__SUNPRO_CC) && (__SUNPRO_CC <= 0x560)
#  define SWIGTEMPLATEDISAMBIGUATOR template
# elif defined(__HP_aCC)
/* Needed even with `aCC -AA' when `aCC -V' reports HP ANSI C++ B3910B A.03.55 */
/* If we find a maximum version that requires this, the test would be __HP_aCC <= 35500 for A.03.55 */
#  define SWIGTEMPLATEDISAMBIGUATOR template
# else
#  define SWIGTEMPLATEDISAMBIGUATOR
# endif
#endif

/* inline attribute */
#ifndef SWIGINLINE
# if defined(__cplusplus) || (defined(__GNUC__) && !defined(__STRICT_ANSI__))
#   define SWIGINLINE inline
# else
#   define SWIGINLINE
# endif
#endif

/* attribute recognised by some compilers to avoid 'unused' warnings */
#ifndef SWIGUNUSED
# if defined(__GNUC__)
#   if !(defined(__cplusplus)) || (__GNUC__ > 3 || (__GNUC__ == 3 && __GNUC_MINOR__ >= 4))
#     define SWIGUNUSED __attribute__ ((__unused__)) 
#   else
#     define SWIGUNUSED
#   endif
# elif defined(__ICC)
#   define SWIGUNUSED __attribute__ ((__unused__)) 
# else
#   define SWIGUNUSED 
# endif
#endif

#ifndef SWIG_MSC_UNSUPPRESS_4505
# if defined(_MSC_VER)
#   pragma warning(disable : 4505) /* unreferenced local function has been removed */
# endif 
#endif

#ifndef SWIGUNUSEDPARM
# ifdef __cplusplus
#   define SWIGUNUSEDPARM(p)
# else
#   define SWIGUNUSEDPARM(p) p SWIGUNUSED 
# endif
#endif

/* internal SWIG method */
#ifndef SWIGINTERN
# define SWIGINTERN static SWIGUNUSED
#endif

/* internal inline SWIG method */
#ifndef SWIGINTERNINLINE
# define SWIGINTERNINLINE SWIGINTERN SWIGINLINE
#endif

/* exporting methods */
#if (__GNUC__ >= 4) || (__GNUC__ == 3 && __GNUC_MINOR__ >= 4)
#  ifndef GCC_HASCLASSVISIBILITY
#    define GCC_HASCLASSVISIBILITY
#  endif
#endif

#ifndef SWIGEXPORT
# if defined(_WIN32) || defined(__WIN32__) || defined(__CYGWIN__)
#   if defined(STATIC_LINKED)
#     define SWIGEXPORT
#   else
#     define SWIGEXPORT __declspec(dllexport)
#   endif
# else
#   if defined(__GNUC__) && defined(GCC_HASCLASSVISIBILITY)
#     define SWIGEXPORT __attribute__ ((visibility("default")))
#   else
#     define SWIGEXPORT
#   endif
# endif
#endif

/* calling conventions for Windows */
#ifndef SWIGSTDCALL
# if defined(_WIN32) || defined(__WIN32__) || defined(__CYGWIN__)
#   define SWIGSTDCALL __stdcall
# else
#   define SWIGSTDCALL
# endif 
#endif

/* Deal with Microsoft's attempt at deprecating C standard runtime functions */
#if !defined(SWIG_NO_CRT_SECURE_NO_DEPRECATE) && defined(_MSC_VER) && !defined(_CRT_SECURE_NO_DEPRECATE)
# define _CRT_SECURE_NO_DEPRECATE
#endif

/* Deal with Microsoft's attempt at deprecating methods in the standard C++ library */
#if !defined(SWIG_NO_SCL_SECURE_NO_DEPRECATE) && defined(_MSC_VER) && !defined(_SCL_SECURE_NO_DEPRECATE)
# define _SCL_SECURE_NO_DEPRECATE
#endif



/* Fix for jlong on some versions of gcc on Windows */
#if defined(__GNUC__) && !defined(__INTEL_COMPILER)
  typedef long long __int64;
#endif

/* Fix for jlong on 64-bit x86 Solaris */
#if defined(__x86_64)
# ifdef _LP64
#   undef _LP64
# endif
#endif

#include <jni.h>
#include <stdlib.h>
#include <string.h>


/* Support for throwing Java exceptions */
typedef enum {
  SWIG_JavaOutOfMemoryError = 1, 
  SWIG_JavaIOException, 
  SWIG_JavaRuntimeException, 
  SWIG_JavaIndexOutOfBoundsException,
  SWIG_JavaArithmeticException,
  SWIG_JavaIllegalArgumentException,
  SWIG_JavaNullPointerException,
  SWIG_JavaDirectorPureVirtual,
  SWIG_JavaUnknownError
} SWIG_JavaExceptionCodes;

typedef struct {
  SWIG_JavaExceptionCodes code;
  const char *java_exception;
} SWIG_JavaExceptions_t;


static void SWIGUNUSED SWIG_JavaThrowException(JNIEnv *jenv, SWIG_JavaExceptionCodes code, const char *msg) {
  jclass excep;
  static const SWIG_JavaExceptions_t java_exceptions[] = {
    { SWIG_JavaOutOfMemoryError, "java/lang/OutOfMemoryError" },
    { SWIG_JavaIOException, "java/io/IOException" },
    { SWIG_JavaRuntimeException, "java/lang/RuntimeException" },
    { SWIG_JavaIndexOutOfBoundsException, "java/lang/IndexOutOfBoundsException" },
    { SWIG_JavaArithmeticException, "java/lang/ArithmeticException" },
    { SWIG_JavaIllegalArgumentException, "java/lang/IllegalArgumentException" },
    { SWIG_JavaNullPointerException, "java/lang/NullPointerException" },
    { SWIG_JavaDirectorPureVirtual, "java/lang/RuntimeException" },
    { SWIG_JavaUnknownError,  "java/lang/UnknownError" },
    { (SWIG_JavaExceptionCodes)0,  "java/lang/UnknownError" }
  };
  const SWIG_JavaExceptions_t *except_ptr = java_exceptions;

  while (except_ptr->code != code && except_ptr->code)
    except_ptr++;

  (*jenv)->ExceptionClear(jenv);
  excep = (*jenv)->FindClass(jenv, except_ptr->java_exception);
  if (excep)
    (*jenv)->ThrowNew(jenv, excep, msg);
}


/* Contract support */

#define SWIG_contract_assert(nullreturn, expr, msg) if (!(expr)) {SWIG_JavaThrowException(jenv, SWIG_JavaIllegalArgumentException, msg); return nullreturn; } else



extern void spg_show_symmetry(const double (*lattice)[3], const double (*position)[3], const int* types, const int num_atom, const double symprec);

extern int spg_find_primitive(const double (*lattice)[3], const double (*position)[3], const int* types, const int num_atom, const double symprec);

extern int spg_get_international(char symbol[11], const double lattice[3][3],
                        const double position[][3],
                        const int types[], const int num_atom,
                        const double symprec);

double (*getVectsArray())[3] {
   return (double(*)[3]) malloc(3*3*sizeof(double));
}

void putInDoubleNby3Array(double (*a)[3], int i, int j, double v) {
   a[i][j] = v;
}

double getFromDoubleNby3Array(double (*a)[3], int i, int j) {
   double result = a[i][j];
   return result;
}

void deleteIntArray(int* t) {
   free(t);
}
void deleteDoubleNby3Array(double (*a)[3]) {
   free(a);
}

double (*getPositionsArray(int size))[3] {
   return (double(*)[3]) malloc(size*3*sizeof(double));
}

int* getTypesArray(int size) {
   return (int*) malloc(size*sizeof(int));
}

int getFromIntArray(int* a, int i) {
   return a[i];
}

void putInIntArray(int* a, int i, int v) {
   a[i] = v;
}



#ifdef __cplusplus
extern "C" {
#endif

SWIGEXPORT void JNICALL Java_spglib_spglibJNI_spg_1show_1symmetry(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jlong jarg3, jint jarg4, jdouble jarg5) {
  double (*arg1)[3] = (double (*)[3]) 0 ;
  double (*arg2)[3] = (double (*)[3]) 0 ;
  int *arg3 = (int *) 0 ;
  int arg4 ;
  double arg5 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(double (**)[3])&jarg1; 
  arg2 = *(double (**)[3])&jarg2; 
  arg3 = *(int **)&jarg3; 
  arg4 = (int)jarg4; 
  arg5 = (double)jarg5; 
  spg_show_symmetry((double const (*)[3])arg1,(double const (*)[3])arg2,(int const *)arg3,arg4,arg5);
}


SWIGEXPORT jint JNICALL Java_spglib_spglibJNI_spg_1find_1primitive(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jlong jarg3, jint jarg4, jdouble jarg5) {
  jint jresult = 0 ;
  double (*arg1)[3] = (double (*)[3]) 0 ;
  double (*arg2)[3] = (double (*)[3]) 0 ;
  int *arg3 = (int *) 0 ;
  int arg4 ;
  double arg5 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(double (**)[3])&jarg1; 
  arg2 = *(double (**)[3])&jarg2; 
  arg3 = *(int **)&jarg3; 
  arg4 = (int)jarg4; 
  arg5 = (double)jarg5; 
  result = (int)spg_find_primitive((double const (*)[3])arg1,(double const (*)[3])arg2,(int const *)arg3,arg4,arg5);
  jresult = (jint)result; 
  return jresult;
}


SWIGEXPORT jint JNICALL Java_spglib_spglibJNI_spg_1get_1international(JNIEnv *jenv, jclass jcls, jstring jarg1, jlong jarg2, jlong jarg3, jlong jarg4, jint jarg5, jdouble jarg6) {
  jint jresult = 0 ;
  char *arg1 ;
  double (*arg2)[3] ;
  double (*arg3)[3] ;
  int *arg4 ;
  int arg5 ;
  double arg6 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = 0;
  if (jarg1) {
    arg1 = (char *)(*jenv)->GetStringUTFChars(jenv, jarg1, 0);
    if (!arg1) return 0;
  }
  arg2 = *(double (**)[3])&jarg2; 
  arg3 = *(double (**)[3])&jarg3; 
  arg4 = *(int **)&jarg4; 
  arg5 = (int)jarg5; 
  arg6 = (double)jarg6; 
  result = (int)spg_get_international(arg1,(double const (*)[3])arg2,(double const (*)[3])arg3,(int const (*))arg4,arg5,arg6);
  jresult = (jint)result; 
  
  if (arg1) (*jenv)->ReleaseStringUTFChars(jenv, jarg1, (const char *)arg1);
  
  
  
  return jresult;
}


SWIGEXPORT jlong JNICALL Java_spglib_spglibJNI_getVectsArray(JNIEnv *jenv, jclass jcls) {
  jlong jresult = 0 ;
  double (*result)[3] = 0 ;
  
  (void)jenv;
  (void)jcls;
  result = (double (*)[3])getVectsArray();
  *(double (**)[3])&jresult = result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_spglib_spglibJNI_putInDoubleNby3Array(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jdouble jarg4) {
  double (*arg1)[3] = (double (*)[3]) 0 ;
  int arg2 ;
  int arg3 ;
  double arg4 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(double (**)[3])&jarg1; 
  arg2 = (int)jarg2; 
  arg3 = (int)jarg3; 
  arg4 = (double)jarg4; 
  putInDoubleNby3Array((double (*)[3])arg1,arg2,arg3,arg4);
}


SWIGEXPORT jdouble JNICALL Java_spglib_spglibJNI_getFromDoubleNby3Array(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
  jdouble jresult = 0 ;
  double (*arg1)[3] = (double (*)[3]) 0 ;
  int arg2 ;
  int arg3 ;
  double result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(double (**)[3])&jarg1; 
  arg2 = (int)jarg2; 
  arg3 = (int)jarg3; 
  result = (double)getFromDoubleNby3Array((double (*)[3])arg1,arg2,arg3);
  jresult = (jdouble)result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_spglib_spglibJNI_deleteIntArray(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  int *arg1 = (int *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(int **)&jarg1; 
  deleteIntArray(arg1);
}


SWIGEXPORT void JNICALL Java_spglib_spglibJNI_deleteDoubleNby3Array(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  double (*arg1)[3] = (double (*)[3]) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(double (**)[3])&jarg1; 
  deleteDoubleNby3Array((double (*)[3])arg1);
}


SWIGEXPORT jlong JNICALL Java_spglib_spglibJNI_getPositionsArray(JNIEnv *jenv, jclass jcls, jint jarg1) {
  jlong jresult = 0 ;
  int arg1 ;
  double (*result)[3] = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  result = (double (*)[3])getPositionsArray(arg1);
  *(double (**)[3])&jresult = result; 
  return jresult;
}


SWIGEXPORT jlong JNICALL Java_spglib_spglibJNI_getTypesArray(JNIEnv *jenv, jclass jcls, jint jarg1) {
  jlong jresult = 0 ;
  int arg1 ;
  int *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  result = (int *)getTypesArray(arg1);
  *(int **)&jresult = result; 
  return jresult;
}


SWIGEXPORT jint JNICALL Java_spglib_spglibJNI_getFromIntArray(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2) {
  jint jresult = 0 ;
  int *arg1 = (int *) 0 ;
  int arg2 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(int **)&jarg1; 
  arg2 = (int)jarg2; 
  result = (int)getFromIntArray(arg1,arg2);
  jresult = (jint)result; 
  return jresult;
}


SWIGEXPORT void JNICALL Java_spglib_spglibJNI_putInIntArray(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3) {
  int *arg1 = (int *) 0 ;
  int arg2 ;
  int arg3 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(int **)&jarg1; 
  arg2 = (int)jarg2; 
  arg3 = (int)jarg3; 
  putInIntArray(arg1,arg2,arg3);
}


#ifdef __cplusplus
}
#endif

