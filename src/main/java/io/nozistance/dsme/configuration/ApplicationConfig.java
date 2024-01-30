package io.nozistance.dsme.configuration;

import io.nozistance.dsme.util.DayOfWeek;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.util.Pair;

import java.net.URI;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
public class ApplicationConfig {

    private final ApplicationProperties applicationProperties;

    @Bean
    public Map<DayOfWeek, URI> uriMap() {
        String uri = applicationProperties.getUriFormat();
        return Arrays.stream(DayOfWeek.values())
                .map(d -> Pair.of(d, uri.formatted(d)))
                .collect(Collectors.toMap(
                        Pair::getFirst, p -> URI.create(p.getSecond()),
                        (x, y) -> y, () -> new EnumMap<>(DayOfWeek.class)
                ));
    }
}
