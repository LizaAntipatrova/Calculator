package validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllowedSymbolConstant {
    //create file with symbols
    public static final Set<String> ALLOWED_SYMBOLS = new HashSet<>(
            List.of(
                    "(",
                    ")",
                    "+",
                    "-",
                    "=",
                    "*",
                    "/"
            ));


}
