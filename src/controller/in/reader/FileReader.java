package controller.in.reader;

import java.io.BufferedReader;
import java.io.File;

public class FileReader implements AbstractReader {

    private final File input;

    public FileReader(File input) throws Exception {
        if (input == null || !input.isFile()) {
            throw new Exception(NO_INPUT);
        }
        this.input = input;
    }

    @Override
    public String getInput() throws Exception {
        BufferedReader bufferedReader;
        String line;

        StringBuilder content = new StringBuilder();

        bufferedReader = new BufferedReader(new java.io.FileReader(input));
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line.trim()).append("\n");
        }
        bufferedReader.close();
        return content.toString();
    }
}