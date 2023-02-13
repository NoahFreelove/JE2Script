package org.JEScript.Patterns;

public class PatternConverter {

    public static String convert(String input){
        int preSpaces = 0;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == ' '){
                preSpaces++;
            }
            else{
                break;
            }
        }
        String trimmedOutput = input.trim();
        if(!trimmedOutput.startsWith("#")){
            return input;

        }
        trimmedOutput = trimmedOutput.substring(1);

        // Split function call
        String[] split = trimmedOutput.split("\\(");
        String functionName = split[0].trim();
        String functionArgs = split[1].trim();
        functionArgs = functionArgs.substring(0, functionArgs.length()-2);

        functionName = replace(functionName);

        trimmedOutput = functionName + "(" + functionArgs + ");";

        trimmedOutput = finalize(preSpaces, new StringBuilder(trimmedOutput));


        return trimmedOutput;
    }

    private static String finalize(int preSpaces, StringBuilder trimmedOutput) {
        // add all the spaces back
        for(int i = 0; i < preSpaces; i++){
            trimmedOutput.insert(0, " ");
        }
        return trimmedOutput.toString();
    }

    private static String replace(String input){
        String replacement = SimpleReplacement.replacements.get(input);
        if(replacement == null){
            return input;
        }
        return replacement;
    }
}
