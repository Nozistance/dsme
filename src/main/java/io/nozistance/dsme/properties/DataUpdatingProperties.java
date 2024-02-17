package io.nozistance.dsme.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("data-updating")
public class DataUpdatingProperties {

    private final String uriFormat;
}
