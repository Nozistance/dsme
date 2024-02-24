package io.nozistance.dsme.service.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.Map;

import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;

@Service
@EnableRetry
@RequiredArgsConstructor
public class DocumentFetchingService {

    private final Map<DayOfWeek, URI> uris;

    @SneakyThrows(IOException.class)
    @Retryable(retryFor = SocketTimeoutException.class)
    public Document getDocument(DayOfWeek day) {
        return Jsoup.parse(uris.get(day).toURL(), 10_000);
    }
}
