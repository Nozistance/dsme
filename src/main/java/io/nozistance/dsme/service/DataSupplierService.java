package io.nozistance.dsme.service;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.util.DayOfWeek;

import java.util.List;

public interface DataSupplierService {

    List<Item> getFor(DayOfWeek dayOfWeek);
}
