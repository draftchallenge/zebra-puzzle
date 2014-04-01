package controller.in.transformer;

import controller.in.reader.AbstractReader;
import model.SolutionTemplate;

public interface AbstractTransformer {

    public abstract SolutionTemplate parse(AbstractReader input) throws Exception;
}