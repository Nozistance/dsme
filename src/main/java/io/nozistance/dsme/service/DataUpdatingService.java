package io.nozistance.dsme.service;

import io.nozistance.dsme.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DataUpdatingService {

    private final DataSupplierService supplierService;
    private final MenuRepository menuRepository;

    @Transactional
    @Scheduled(cron = "${data.update-frequency}")
    public void update() {
        menuRepository.deleteAllInBatch();
        var items = supplierService.get();
        menuRepository.saveAll(items);
    }
}
