package io.nozistance.dsme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

@Service
@RequiredArgsConstructor
public class ImageFetchingService {

    private final RestTemplate restTemplate;

    @Cacheable("image-fetching")
    @Retryable(retryFor = SocketTimeoutException.class)
    public byte[] fetchFrom(String path) {
        return restTemplate.getForEntity(path, byte[].class).getBody();
    }
}
