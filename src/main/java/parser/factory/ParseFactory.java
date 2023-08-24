package parser.factory;

import parser.Parsable;
import parser.imp.GraphParser;

public class ParseFactory {

    public static Parsable createParser(ParseType type) {
        Parsable parser = null;
        if (type == ParseType.SIMPLE) {
            parser = new GraphParser();
        }
        return parser;
    }

    public static Parsable createSimpleParser() {
        return createParser(ParseType.SIMPLE);
    }
}
