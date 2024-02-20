package io.nozistance.dsme.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;

@Service
@EnableRetry
public class DocumentFetchingService {

    @SneakyThrows(IOException.class)
    @Retryable(retryFor = SocketTimeoutException.class)
    public Document getDocument(URI uri) {
        return Jsoup.parse(uri.toURL(), 10_000);
    }
}
