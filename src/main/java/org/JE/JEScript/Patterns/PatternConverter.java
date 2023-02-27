package org.JE.JEScript.Patterns;

import org.JE.JEScript.Compiler.Compiler;

public class PatternConverter {

    private static boolean inComment = false;
    private static boolean inTryCatch = false;
    private static String className;
    public static String convert(String input){

        // Detect class name
        if(input.startsWith("public class") || input.startsWith("class")){
            className = input.replace("public class", "").split(" ")[1].trim();
        }

        // Ignore Comments
        if(input.startsWith("//")){
            return input;
        }

        if(input.contains("/*")){
            inComment = true;
        }
        if(input.contains("*/")){
            inComment = false;
        }
        if(inComment){
            return "";
        }

        // Keep space formatting
        int preSpaces = 0;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == ' '){
                preSpaces++;
            }
            else{
                break;
            }
        }
        // Check for sketchy try catch
        String trimmedOutput = input.trim();
        if(trimmedOutput.startsWith("???"))
        {
            inTryCatch = true;
            trimmedOutput = trimmedOutput.replace("???", "try{");
        }

        if(trimmedOutput.startsWith(":(") && inTryCatch)
        {
            inTryCatch = false;
            trimmedOutput = "}catch(Exception ignored){" + trimmedOutput.replace(":(", "") + "}";
        }

        // Check for dynamic commands
        if(trimmedOutput.startsWith("$"))
        {
            ReplacementResult rr = DynamicCommands.replace(trimmedOutput, className);
            trimmedOutput = rr.result();
            Compiler.addImport(rr.importStatement());
        }

        // don't miss imports!
        if(trimmedOutput.startsWith("import")){
            Compiler.addImport(trimmedOutput + ";");
            return "";
        }

        // Where bulk of processing goes down
        boolean funcDecl = trimmedOutput.startsWith("event");
        trimmedOutput = varReplace(trimmedOutput);
        trimmedOutput = commandReplace(trimmedOutput);
        trimmedOutput = newReplace(trimmedOutput);
        trimmedOutput = equalReplace(trimmedOutput);
        trimmedOutput = friendReplace(trimmedOutput);

        if(!funcDecl){
            trimmedOutput = shortcutReplace(trimmedOutput);
        }

        boolean addSemicolon = !trimmedOutput.endsWith("}") && !trimmedOutput.endsWith("{") && !trimmedOutput.endsWith("(");
        if(funcDecl)
            addSemicolon = false;
        trimmedOutput = finalize(preSpaces, new StringBuilder(trimmedOutput), addSemicolon);

        return trimmedOutput;
    }
    // replace things like object.position = xyz; to object.getTransform().setPosition(xyz);
    // replace things like object.position; to object.getTransform().getPosition();
    private static String shortcutReplace(String input){

        // only run if position, rotation, or scale is in the string
        if(!input.contains("position") && !input.contains("rotation") && !input.contains("scale") && !input.contains("active")){
            return input;
        }

        // if it is position-><something> then replace it with getTransform().setPosition(<something>)
        if(input.contains("position->")){
            input = input.replace("position->", "getTransform().setPosition(");
            input = input + ")";
        }
        // if it is position then replace it with getTransform().getPosition()
        if(input.contains("position()")){
            input = input.replace("position()", "getTransform().position()");
        }
        // if it is rotation-><something> then replace it with getTransform().setRotation(<something>)
        if(input.contains("rotation->")){
            input = input.replace("rotation->", "getTransform().setRotation(");
            input = input + ")";
        }
        // if it is rotation then replace it with getTransform().getRotation()
        if(input.contains("rotation()")){
            input = input.replace("rotation()", "getTransform().rotation()");
        }
        // if it is scale-><something> then replace it with getTransform().setScale(<something>)
        if(input.contains("scale->")){
            input = input.replace("scale->", "getTransform().setScale(");
            input = input + ")";
        }
        // if it is scale then replace it with getTransform().getScale()
        if(input.contains("scale()")){
            input = input.replace("scale()", "getTransform().scale()");
        }

        // if it is active-><something> then replace it with setActive(<something>)
        if(input.contains("active->")){
            input = input.replace("active->", "setActive(");
            input = input + ")";
        }
        // if it is active then replace it with getActive()
        if(input.contains("active()")){
            input = input.replace("active()", "getActive()");
        }

        return input;
    }

    private static String equalReplace(String input){
        // if NOT in a string, replace : with =
        // go through each character
        boolean inString = false;
        for(int i = 0; i < input.length(); i++){
            // if it is a ", then toggle inString
            if(input.charAt(i) == '"'){
                inString = !inString;
            }
            // if it is a : and not in a string, replace it with =
            else if(input.charAt(i) == ':' && !inString){
                if(i+1 < input.length()){
                    if(!(input.charAt(i+1) == ':') && !(input.charAt(i+1) == ')')){
                        input = input.substring(0, i) + "=" + input.substring(i + 1);
                    }
                    else i++;
                }
                else
                    input = input.substring(0, i) + "=" + input.substring(i + 1);
            }
        }
        return input;
    }

    private static String varReplace(String input){

        // foreach command in CommandReplacement, replace it with the value
        for (String key : VarReplacement.replacements.keySet()) {
            if(input.contains(key)){
                ReplacementResult rr = VarReplacement.replacements.get(key);
                if(!rr.importStatement().equals("")){
                    Compiler.addImport((rr.importStatement()));
                }
                input = input.replace(key, rr.result());
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
                ReplacementResult rr = CommandReplacement.replacements.get(key);
                if(!rr.importStatement().equals("")){
                    Compiler.addImport((rr.importStatement()));
                }
                input = input.replace("#" + key, rr.result());

            }
        }

        return input;
    }

    private static String newReplace(String input){
        input = input.replace("**", "new ");

        return input;
    }

    private static String friendReplace(String input){
        if(!input.startsWith(":)"))
            return input;

        String[] split = input.split(" ");
        String name = split[1];
        String className = split[2];
        String[] argSplit = input.split("\\(")[1].split("\\)")[0].split(",");
        for (int i = 0; i < argSplit.length; i++) {
            if(argSplit[i].startsWith(" ")){
                argSplit[i] = argSplit[i].substring(1);
            }
        }
        String[] argClasses = new String[argSplit.length];
        String[] argNames = new String[argSplit.length];

        if(argSplit.length > 0 ){
            for (int i = 0; i < argSplit.length; i++) {
                String[] data = argSplit[i].split(" ");
                if(data.length>1)
                {
                    argClasses[i] = data[0];
                    argNames[i] = data[1];
                }
            }
        }

        String result = "";
        result += "public final Friend " + name + " = new Friend(" + className + ".class.getName(), args-> { ";
        for (int i = 0; i < argClasses.length; i++) {
            if(argClasses[i] == null)
                continue;
            result += argClasses[i] + " " + argNames[i] + " = (" + argClasses[i] + ")args[" + i + "];";
        }
        result = result.substring(0, result.length() - 1);
        Compiler.addImport("import org.JE.JE2.Utility.Friend;");

        return result;
    }
}
