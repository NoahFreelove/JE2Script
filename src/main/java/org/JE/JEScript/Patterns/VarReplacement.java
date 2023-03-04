package org.JE.JEScript.Patterns;

import java.util.HashMap;

public class VarReplacement {

    public static HashMap<String, ReplacementResult> replacements = new HashMap<>();

    static {
        replacements.put("vec3", new ReplacementResult("Vector3f","import org.joml.Vector3f;"));
        replacements.put("Vector3f", new ReplacementResult("Vector3f","import org.joml.Vector3f;"));
        replacements.put("vec3i", new ReplacementResult("Vector3i","import org.joml.Vector3i;"));
        replacements.put("Vector3i", new ReplacementResult("Vector3i","import org.joml.Vector3i;"));
        replacements.put("vec2", new ReplacementResult("Vector2f","import org.joml.Vector2f;"));
        replacements.put("Vector2f", new ReplacementResult("Vector2f","import org.joml.Vector2f;"));
        replacements.put("vec2i", new ReplacementResult("Vector2i","import org.joml.Vector2i;"));
        replacements.put("Vector2i", new ReplacementResult("Vector2i","import org.joml.Vector2i;"));

        replacements.put("bool ", new ReplacementResult("boolean ",""));
        replacements.put("string", new ReplacementResult("String",""));
        replacements.put("Str", new ReplacementResult("String",""));
        replacements.put("GO", new ReplacementResult("GameObject","import org.JE.JE2.Objects.GameObject;"));
        replacements.put("gameobject", new ReplacementResult("GameObject","import org.JE.JE2.Objects.GameObject;"));
        replacements.put("GameObject", new ReplacementResult("GameObject","import org.JE.JE2.Objects.GameObject;"));
        replacements.put("cam", new ReplacementResult("Camera","import org.JE.JE2.Rendering.Camera;"));
        replacements.put("run", new ReplacementResult("()->",""));
        replacements.put("lam", new ReplacementResult("this::",""));


    }
}
