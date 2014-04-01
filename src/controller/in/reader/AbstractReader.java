package controller.in.reader;

public interface AbstractReader {

    static final String NO_INPUT = "No input";

    public abstract String getInput() throws Exception;
}
