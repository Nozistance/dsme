package io.nozistance.dsme.service;

import io.nozistance.dsme.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var items = dataSupplierService.get();
        menuRepository.saveAll(items);
    }
}
