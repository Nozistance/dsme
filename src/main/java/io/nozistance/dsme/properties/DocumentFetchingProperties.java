package io.nozistance.dsme.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("data.document-fetching")
public class DocumentFetchingProperties {

    private final String path;
}
