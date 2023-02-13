package org.JEScript;

import org.JEScript.Compiler.Compiler;

import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        File script = new File("src/main/java/org/JEScript/example.jes");

        try {
            Compiler.compile(script, new HashMap<>());
        }catch (Exception e){}
        System.out.println(script.getAbsolutePath());
    }
}