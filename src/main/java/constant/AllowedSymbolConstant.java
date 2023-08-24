package constant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllowedSymbolConstant {
    //create file with symbols
    public static final String OPENED_BRACKET = "(";
    public static final String CLOSED_BRACKET = ")";


    public static final Set<String> ALLOWED_OPERATORS = new HashSet<>(
            List.of(
                    "+",
                    "-",
                    "*",
                    "/"
            ));
    public static final Set<String> ALLOWED_SYMBOLS = new HashSet<>(
            List.of(
                    "(",
                    ")",
                    "+",
                    "-",
                    "*",
                    "/",
                    "."
            ));


}
