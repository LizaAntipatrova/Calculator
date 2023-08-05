package spliter;

import java.util.List;

@FunctionalInterface
public interface Splitable {

    List<String> split(String input);
}
