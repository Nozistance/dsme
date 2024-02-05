package io.nozistance.dsme.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("text")
public class TextProperties {

    private String keyboardCancel;
    private String dayKeyboard;
    private String size;
}
