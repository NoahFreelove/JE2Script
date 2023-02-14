package org.JEScript.Patterns;

import java.util.HashMap;

public class VarReplacement {

    public static HashMap<String, String> replacements = new HashMap<>();

    static {
        replacements.put("vec3", "Vector3f");
        replacements.put("vec3i", "Vector3i");
        replacements.put("vec2", "Vector2f");
        replacements.put("vec2i", "Vector2i");
        replacements.put("bool", "boolean");
        replacements.put("string", "String");
        replacements.put("str", "String");
        replacements.put("GO", "GameObject");
        replacements.put("gameObject", "GameObject");
        replacements.put("gameobject", "GameObject");

    }
}
