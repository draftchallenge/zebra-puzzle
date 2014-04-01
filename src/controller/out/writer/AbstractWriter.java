package controller.out.writer;

public interface AbstractWriter {

    public void write(String content);

    public void setOptions(String... opts);
}
