package controller.in.reader;

public class StringReader implements AbstractReader {

    public static final String NO_INPUT = "No input";

    private final String content;

    public StringReader(String input) throws Exception {
        if (input == null || input.equals("")) {
            throw new Exception(NO_INPUT);
        }
        this.content = input;
    }

    @Override
    public String getInput() {
        return content;
    }

}
