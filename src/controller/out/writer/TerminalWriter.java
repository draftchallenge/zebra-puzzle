package controller.out.writer;

public class TerminalWriter implements AbstractWriter {

    @Override
    public void write(String content) {
        System.out.println(content);
    }

    @Override
    public void setOptions(String... opts) {
    }

}
