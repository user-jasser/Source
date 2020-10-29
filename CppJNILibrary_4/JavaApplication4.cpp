// Native methods implementation of
// /home/napo1eon/myproject/Source/JavaApplication4/src/javaapplication4/JavaApplication4.java

#include <iostream>

#include "JavaApplication4.h"

void JNICALL Java_javaapplication4_JavaApplication4_say
  (JNIEnv * env, jclass object, jstring param1) {
    
    const char *nativeString = env->GetStringUTFChars(param1, 0);
    std::cout << nativeString << std::endl;
    env->ReleaseStringUTFChars(param1, nativeString);
}
