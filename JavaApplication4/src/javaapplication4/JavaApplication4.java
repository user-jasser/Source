/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

/**
 *
 * @author napo1eon
 */
public class JavaApplication4 {

    
    public static native void say(String str);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.loadLibrary("CppJNILibrary_4");
        System.out.println("HelloWorld");
        say("THIS MY");
    }
    
}
