package controller.out;

import controller.out.formatter.AbstractFormatter;
import controller.out.formatter.PlainFormatter;
import controller.out.formatter.XMLFormatter;
import controller.out.writer.AbstractWriter;
import controller.out.writer.FileWriter;
import controller.out.writer.TerminalWriter;

public class FactoryOut {

    public static AbstractWriter getWriter(OUTPUT output) {
        if (output == OUTPUT.FILE) {
            return new FileWriter();
        } else if (output == OUTPUT.TERMINAL) {
            return new TerminalWriter();
        }
        return null;
    }

    public static AbstractFormatter getFormatter(FORMAT format) {
        if (format == FORMAT.XML) {
            return new XMLFormatter();
        } else if (format == FORMAT.PLAIN) {
            return new PlainFormatter();
        }
        return null;
    }

    public enum FORMAT {
        XML, PLAIN
    }

    public enum OUTPUT {
        FILE, TERMINAL
    }
}
