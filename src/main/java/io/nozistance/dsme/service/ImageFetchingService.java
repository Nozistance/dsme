package io.nozistance.dsme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ImageFetchingService {

    private final RestTemplate restTemplate;

    @Cacheable("image-fetching")
    public byte[] fetchFrom(String path) {
        return restTemplate.getForEntity(path, byte[].class).getBody();
    }
}
