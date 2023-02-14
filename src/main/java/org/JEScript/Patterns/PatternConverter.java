package org.JEScript.Patterns;

public class PatternConverter {

    public static String convert(String input){

        if(input.startsWith("//")){
            return input;
        }

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

        boolean funcDecl = trimmedOutput.startsWith("event");
        // Split function call
        String[] bracketSplit = trimmedOutput.substring(1).split("\\(");

        trimmedOutput = varReplace(trimmedOutput);

        trimmedOutput = commandReplace(trimmedOutput);

        if(funcDecl){
            String eventName = trimmedOutput.replace("event", "").split("\\(")[0].trim();
            trimmedOutput = eventReplace(eventName, trimmedOutput.contains("{"));
        }
        else{
            trimmedOutput = shortcutReplace(trimmedOutput);
        }
        boolean addSemicolon = !trimmedOutput.endsWith("}") && !trimmedOutput.endsWith("{");
        if(funcDecl)
            addSemicolon = false;
        trimmedOutput = finalize(preSpaces, new StringBuilder(trimmedOutput), addSemicolon);

        return trimmedOutput;
    }
    // replace things like object.position = xyz; to object.getTransform().setPosition(xyz);
    // replace things like object.position; to object.getTransform().getPosition();
    private static String shortcutReplace(String input){

        // only run if position, rotation, or scale is in the string
        if(!input.contains("position") && !input.contains("rotation") && !input.contains("scale")){
            return input;
        }

        // if it is position-><something> then replace it with getTransform().setPosition(<something>)
        if(input.contains("position->")){
            input = input.replace("position->", "getTransform().setPosition(");
            input = input + ")";
        }
        // if it is position then replace it with getTransform().getPosition()
        else if(input.contains("position")){
            input = input.replace("position", "getTransform().position()");
        }
        // if it is rotation-><something> then replace it with getTransform().setRotation(<something>)
        if(input.contains("rotation->")){
            input = input.replace("rotation->", "getTransform().setRotation(");
            input = input + ")";
        }
        // if it is rotation then replace it with getTransform().getRotation()
        else if(input.contains("rotation")){
            input = input.replace("rotation", "getTransform().rotation()");
        }
        // if it is scale-><something> then replace it with getTransform().setScale(<something>)
        if(input.contains("scale->")){
            input = input.replace("scale->", "getTransform().setScale(");
            input = input + ")";
        }
        // if it is scale then replace it with getTransform().getScale()
        else if(input.contains("scale")){
            input = input.replace("scale", "getTransform().scale()");
        }
        return input;
    }

    private static String varReplace(String input){

        // foreach command in CommandReplacement, replace it with the value
        for (String key : VarReplacement.replacements.keySet()) {
            if(input.contains(key)){
                input = input.replace(key, VarReplacement.replacements.get(key));
            }
        }

        return input;
    }


    private static String finalize(int preSpaces, StringBuilder trimmedOutput, boolean addSemicolon){
        // add all the spaces back
        for(int i = 0; i < preSpaces; i++){
            trimmedOutput.insert(0, " ");
        }
        if(addSemicolon)
        {
            trimmedOutput.append(";");
        }
        return trimmedOutput.toString();
    }

    private static String commandReplace(String input){

        // foreach command in CommandReplacement, replace it with the value
        for (String key : CommandReplacement.replacements.keySet()) {
            if(input.contains(key)){
                input = input.replace("#" + key, CommandReplacement.replacements.get(key));
            }
        }

        return input;
    }

    private static String eventReplace(String input, boolean addBrackets){
        String replacement = switch (input) {
            case "start" -> "public void start()";
            case "update" -> "public void update()";
            default -> input;
        };
        if(addBrackets){
            replacement = replacement + "{";
        }
        return replacement;
    }
}
