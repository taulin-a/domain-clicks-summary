package org.domain.clicks.summary.components;

import org.domain.clicks.summary.model.domain.DomainCountResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DomainCounter {
    private static final String DOMAIN_SEPARATOR_REGEX = "\\.";
    private static final String DOMAIN_SEPARATOR = ".";

    private static DomainCounter instance;

    private DomainCounter() {
    }

    public DomainCountResult count(List<List<String>> csvReaderOutput) {
        Map<String, Long> domainClickSumMap = csvReaderOutput.stream()
                .map(this::getDomainMapFromList)
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
        return new DomainCountResult(domainClickSumMap);
    }

    private Map<String, Long> getDomainMapFromList(List<String> domainDataList) {
        Long clickCount = Long.parseLong(domainDataList.get(0));
        Map<String, Long> domainMap = new HashMap<>();
        String[] domainParts = domainDataList.get(1).split(DOMAIN_SEPARATOR_REGEX);
        for (int i = 0; i < domainParts.length; i++) {
            StringBuilder domainNameBuilder = new StringBuilder(domainParts[i]);
            for (int j = i + 1; j < domainParts.length; j++) {
                domainNameBuilder.append(DOMAIN_SEPARATOR).append(domainParts[j]);
            }
            domainMap.put(domainNameBuilder.toString(), clickCount);
        }

        return domainMap;
    }

    public static DomainCounter getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DomainCounter();
        }
        return instance;
    }
}
