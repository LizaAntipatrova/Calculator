package spliter.impl;

import spliter.Splitable;

import java.util.List;

public class SimpleSpliterImpl implements Splitable {
    @Override
    public List<String> split(String input) {
        return List.of(input.split(""));
    }
}
