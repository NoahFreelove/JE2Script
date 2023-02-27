package org.JE.JEScript.Patterns;

public class DynamicCommands {

    public static ReplacementResult replace(String input, String className){
        String trimmedInput = input.replace("$", "").trim();
        String importStatement = "";
        if(trimmedInput.startsWith("construct")) {

            if (trimmedInput.contains("{"))
            {
                trimmedInput = "public " + className + "(){";
            }
            else
            {
                trimmedInput = "public " + className + "()";
            }
        }
        if(trimmedInput.startsWith("event ")) {
            trimmedInput = trimmedInput.replace("event ", "@Override public void ");
        }

        return new ReplacementResult(trimmedInput,importStatement);
    }
}
