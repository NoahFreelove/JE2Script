package org.JEScript.Patterns;

import java.util.HashMap;

public class SimpleReplacement {

    public static HashMap<String, String> replacements = new HashMap<>();

    static {
        replacements.put("print", "System.out.println");
        replacements.put("println", "System.out.println");

    }
}
