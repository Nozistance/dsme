package io.nozistance.dsme.service;

import io.nozistance.dsme.repository.MenuRepository;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataUpdatingService {

    private final DataSupplierService dataSupplierService;
    private final MenuRepository menuRepository;

    @Transactional
    @Scheduled(cron = "${data-updating.update-frequency}")
    public void update() {
        menuRepository.deleteAllInBatch();
        menuRepository.saveAll(stream(DayOfWeek.values())
                .parallel().map(dataSupplierService::getFor)
                .flatMap(List::stream)
                .toList());
    }
}
