package org.JEScript;

import org.JEScript.Compiler.Compiler;

import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String filename = "example.jes";

         if(args.length > 0)
        {
            filename = args[0];
        }
         testCompile(filename, 1);
    }

    public static void testCompile(String filename, int times){

        long startTime = System.currentTimeMillis();
        File script = new File(filename);
        File script2 = new File(filename.replace("example", "example2"));

        for (int i = 0; i < times; i++) {
            try {
                if(i%2==0)
                {
                    Compiler.compile(script, new HashMap<>());
                }
                else
                    Compiler.compile(script2, new HashMap<>());

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Compiled all in " + (endTime - startTime) + "ms");
    }
}