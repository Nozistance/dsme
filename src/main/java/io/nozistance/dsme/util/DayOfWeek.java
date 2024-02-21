package io.nozistance.dsme.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DayOfWeek {

    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private final String day;

    @Override
    public String toString() {
        return day;
    }
}
