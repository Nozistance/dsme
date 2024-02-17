package io.nozistance.dsme.service;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.util.DayOfWeek;

import java.util.List;

public interface MenuService {

    List<Item> searchByName(String name);
    List<Item> searchByDay(DayOfWeek day);
}
