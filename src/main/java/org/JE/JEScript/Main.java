package org.JE.JEScript;

import org.JE.JEScript.Compiler.Compiler;

import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String filename = "TestScript.jes";

         if(args.length > 0)
        {
            filename = args[0];
        }
         Tools.compileAll("src");
         //testCompile(filename, 1);
    }

    public static void testCompile(String filename, int times){

        long startTime = System.currentTimeMillis();
        File script = new File(filename);

        for (int i = 0; i < times; i++) {
            try {
                Compiler.compile(script, new HashMap<>());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Compiled all in " + (endTime - startTime) + "ms");
    }
}