package org.domain.clicks.summary.model.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public record DomainCountResult(Map<String, Long> domainSumMap) {

    public DomainCountResult {
        domainSumMap = new HashMap<>(domainSumMap);
    }

    @Override
    public String toString() {
        return domainSumMap.entrySet().stream()
                .map(e -> String.format("%-25s %d", e.getKey() + ":", e.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
