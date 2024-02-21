package io.nozistance.dsme.service;

import io.nozistance.dsme.model.Item;

import java.util.List;
import java.util.function.Supplier;

@FunctionalInterface
public interface DataSupplierService extends Supplier<List<Item>> {

}
