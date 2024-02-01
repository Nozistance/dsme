package io.nozistance.dsme.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InitialDataUpdatingService implements ApplicationRunner {

    private final DataUpdatingService dataUpdatingService;

    @Override
    public void run(ApplicationArguments args) {
        dataUpdatingService.updateData();
    }
}
