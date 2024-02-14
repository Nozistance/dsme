package io.nozistance.dsme.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@AllArgsConstructor
@ConfigurationProperties("text")
public class TextProperties {

    private Map<String, String> answers;
}
