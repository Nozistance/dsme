package io.nozistance.dsme.configuration;

import io.nozistance.dsme.properties.DataProperties;
import io.nozistance.dsme.properties.DocumentFetchingProperties;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;

import java.net.URI;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class DocumentFetchingConfig {

    private final DocumentFetchingProperties properties;
    private final DataProperties dataProperties;

    @Bean
    public Map<DayOfWeek, URI> uriMap() {
        String format = dataProperties.getUriFormat();
        String uri = format + properties.getPath();
        return Arrays.stream(DayOfWeek.values())
                .map(d -> Pair.of(d, uri.formatted(d)))
                .collect(Collectors.toMap(
                        Pair::getFirst, p -> URI.create(p.getSecond()),
                        (x, y) -> y, () -> new EnumMap<>(DayOfWeek.class)
                ));
    }
}
