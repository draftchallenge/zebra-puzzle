import controller.Solver;
import controller.in.FactoryIn;
import controller.in.reader.AbstractReader;
import controller.in.transformer.AbstractTransformer;
import controller.out.FactoryOut;
import controller.out.formatter.AbstractFormatter;
import controller.out.writer.AbstractWriter;
import model.Solution;
import model.SolutionTemplate;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static final String ROOT_DIR = "ressources/"

    public static final String INPUT_FILENAME = ROOT_DIR + "input.cvs";
    public static final FactoryIn.FORMAT INPUT_FORMAT = FactoryIn.FORMAT.CVS;

    public static final FactoryOut.FORMAT OUTPUT_FORMAT = FactoryOut.FORMAT.XML;
    public static final FactoryOut.OUTPUT OUTPUT_DESTINATION = FactoryOut.OUTPUT.FILE;
    public static final String OUTPUT_FILENAME = ROOT_DIR + "output.xml";

    public static void main(String[] args) throws Exception {
        SolutionTemplate template = readFromFile();

        Solver solver = new Solver(template);
        ArrayList<Solution> solutions = solver.start();

        solver.printStats();
        writeToFile(solutions);
    }

    private static SolutionTemplate readFromFile() throws Exception {
        File file = new File(INPUT_FILENAME);
        AbstractReader reader = FactoryIn.getReader(file);
        AbstractTransformer transformer = FactoryIn.getTransformer(INPUT_FORMAT);
        return transformer.parse(reader);
    }

    private static void writeToFile(ArrayList<Solution> solutions) {
        AbstractFormatter formatter = FactoryOut.getFormatter(OUTPUT_FORMAT);
        AbstractWriter writer = FactoryOut.getWriter(OUTPUT_DESTINATION);
        writer.setOptions(OUTPUT_FILENAME);

        String output = formatter.format(solutions.toArray(new Solution[solutions.size()]));
        writer.write(output);
    }


}
