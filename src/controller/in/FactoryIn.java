package controller.in;

import controller.in.reader.AbstractReader;
import controller.in.reader.FileReader;
import controller.in.reader.StringReader;
import controller.in.transformer.AbstractTransformer;
import controller.in.transformer.CSVTransformer;

import java.io.File;

public class FactoryIn {

    public static AbstractReader getReader(Object input) throws Exception {
        if (input instanceof File) {
            return new FileReader((File) input);
        }
        if (input instanceof String) {
            return new StringReader((String) input);
        }
        return null;
    }

    public static AbstractTransformer getTransformer(FORMAT format) {
        if (format == FORMAT.CVS) {
            return new CSVTransformer(";", "\\r?\\n");
        }
        return null;
    }

    public enum FORMAT {
        CVS
    }
}
