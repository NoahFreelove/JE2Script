package org.JEScript.Patterns;

import java.util.HashMap;

public class CommandReplacement {

    public static HashMap<String, String> replacements = new HashMap<>();

    static {
        replacements.put("print", "System.out.println");
        replacements.put("println", "System.out.println");
        replacements.put("log", "Logger.log");
        replacements.put("queue", "Manager.queueGLFunction");
        replacements.put("parent", "getAttachedObject");
        replacements.put("ts", "toString");

    }
}
