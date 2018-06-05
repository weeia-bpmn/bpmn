package bpmnproject.bpmn.utils;


import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * Created by Dawid on 05.06.2018 at 22:39.
 */

@RequiredArgsConstructor
public class MapUtils {
    private final Map<String, Object> variables;

    @SuppressWarnings("unchecked")
    public <T> T getValue(String variableName) {
        return (T)variables.get(variableName);
    }
}
