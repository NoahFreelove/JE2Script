package org.JEScript.Patterns;

import java.util.HashMap;

public class CommandReplacement {

    public static HashMap<String, ReplacementResult> replacements = new HashMap<>();

    static {
        replacements.put("print", new ReplacementResult("System.out.println",""));
        replacements.put("println", new ReplacementResult("System.out.println",""));
        replacements.put("log", new ReplacementResult("Logger.log","import JE.IO.Logging.Logger;"));
        replacements.put("queue", new ReplacementResult("Manager.queueGLFunction","import JE.Manager;"));
        replacements.put("parent", new ReplacementResult("getAttachedObject",""));
        replacements.put("ts", new ReplacementResult("toString",""));
        replacements.put("mainCamera", new ReplacementResult("Manager.getMainCamera","import JE.Manager;"));

    }
}
