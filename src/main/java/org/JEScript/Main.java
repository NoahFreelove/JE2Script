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

        File script = new File(filename);

        try {
            Compiler.compile(script, new HashMap<>());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}