package org.JEScript.Compiler;

import org.JEScript.Patterns.PatternConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Compiler {

    private static ArrayList<String> imports = new ArrayList<>();

    public static void addImport(String s) {
        if(s.equals(""))
            return;
        if (!imports.contains(s)) {
            imports.add(s);
        }
    }

    public static CompileResult compile(File file, HashMap<String, Object> args) throws IOException {
        imports.clear();
        long startTime = System.currentTimeMillis();
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            // if line ends with semicolon, remove it
            if (line.endsWith(";")) {
                line = line.substring(0, line.length() - 1);
            }
            if (line.startsWith("//") || line.isEmpty()) {
                lines.remove(i);
                i--;
            }
            else {
                lines.set(i, PatternConverter.convert(line));
            }
        }
        // add all imports to beginning of lines
        for (String s : imports) {
            lines.add(1, s);
        }

        // save to file named <filename>.java
        // get filepath
        String filepath = file.getAbsolutePath();
        String filename = file.getName();
        String[] split = filename.split("\\.");
        String newFilename = filepath.substring(0, filepath.length() - filename.length()) + split[0] + ".java";
        File newFile = new File(newFilename);
        write(newFile, lines.toArray(new String[0]));
        long endTime = System.currentTimeMillis();
        System.out.println("Compiled " + filename + " in " + (endTime - startTime) + "ms");
        return CompileResult.SUCCESS;
    }

    private static void write(File f, String[] lines) throws IOException {
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String line : lines) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }
}
