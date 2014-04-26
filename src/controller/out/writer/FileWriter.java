package controller.out.writer;

import java.io.IOException;

public class FileWriter implements AbstractWriter {

    private String fileName;

    @Override
    public void write(String content) {
        String file = fileName == null ? "output" : fileName;

        try {
            java.io.FileWriter writer = new java.io.FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setOptions(String... opts) {
        this.fileName = opts[0];
    }

}
