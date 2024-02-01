package io.nozistance.dsme.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("bot")
public class BotProperties {

    private final String path;
    private final String name;
    private final String token;
}
