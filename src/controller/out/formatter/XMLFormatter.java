package controller.out.formatter;

import model.Solution;
import model.Value;
import model.ValueContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XMLFormatter implements AbstractFormatter {

    @Override
    public String format(Solution[] solutions) {

        String root = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"stylesheet.xsl\" ?>";

        String solutionsAsXML = getSolutionsAsXML(solutions);
        root += newNode(0, "solutions", solutionsAsXML, null);

        return root.trim();
    }

    private String getSolutionsAsXML(Solution[] solutions) {
        StringBuilder solutionXML = new StringBuilder();
        for (Solution solution : solutions) {
            String valuesXML = getValuesAsXML(solution);
            String node = newNode(1, "solution", valuesXML, null);
            solutionXML.append(node);
        }

        return solutionXML.toString();
    }

    private String getValuesAsXML(Solution solution) {
        StringBuilder valuesXML = new StringBuilder();

        for (ValueContainer valueContainer : solution.getValueContainers()) {
            HashMap<String, String> attributes = new HashMap<String, String>();
            Map<String, Value> values = valueContainer.getValues();

            for (String value : values.keySet()) {
                Value current = values.get(value);
                if (current == null) {
                    attributes.put(value, "");
                } else {
                    attributes.put(value, current.getValue());
                }
            }
            String node = newNode(2, "house", "", attributes);
            valuesXML.append(node);
        }

        return valuesXML.toString();
    }

    private String newNode(int indent, String name, String text, HashMap<String, String> attributes) {
        if (text == null) {
            text = "";
        }

        String pre = "";
        for (int i = 0; i < indent; i++) {
            pre += "\t";
        }

        StringBuilder attr = new StringBuilder();
        if (attributes != null) {
            Set<String> keys = attributes.keySet();
            for (String key : keys) {
                attr.append(" ").append(key).append("=\"").append(attributes.get(key)).append("\"");
            }
            if (attr.length() > 0) {
                attr.append(" ");
            }
        }

        StringBuilder ret = new StringBuilder();
        ret.append(pre);
        if (text.length() != 0) {
            ret.append("<").append(name).append(attr).append(">");
            ret.append("\n");
        } else {
            ret.append("<").append(name).append(attr).append("/>\n");
        }

        if (text.length() != 0) {
            ret.append(text).append("").append(pre).append("</").append(name).append(">\n");
        }
        return ret.toString();
    }
}
