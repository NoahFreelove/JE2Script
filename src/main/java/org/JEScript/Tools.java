package org.JEScript;

import org.JEScript.Compiler.Compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tools {

    private static String[] getFiles(File input){
        ArrayList<String> files = new ArrayList<>();
        for (File f : input.listFiles()) {
            if(f.isDirectory()){
                files.addAll(Arrays.asList(getFiles(f)));
            }
            else{
                files.add(f.getAbsolutePath());
            }
        }
        return files.toArray(new String[0]);
    }

    public static void compileAll(String src){
        String[] files = getFiles(new File(src));

        for (String file : files) {
            if(file.endsWith(".jes")){
                System.out.println("Compiling " + file);
                try {
                    Compiler.compile(new File(file), new HashMap<>());
                }
                catch (Exception e){
                    System.out.println("Failed to compile " + file);
                    e.printStackTrace();
                }
            }

        }
    }
}
