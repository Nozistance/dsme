package io.nozistance.dsme.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DayOfWeek {

    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private final String day;

    public static DayOfWeek from(String day) {
        for (DayOfWeek value : values()) {
            if (value.day.equals(day)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown day of week: " + day);
    }

    public static boolean isDayOfWeek(String day) {
        return Arrays.stream(DayOfWeek.values())
                .map(DayOfWeek::getDay)
                .toList().contains(day);
    }

    @Override
    public String toString() {
        return day;
    }
}
