package io.nozistance.dsme.util;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum DayOfWeek {

    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private static final Map<String, DayOfWeek> days;

    static {
        days = Arrays.stream(DayOfWeek.values())
                .collect(Collectors.toMap(
                        DayOfWeek::toString,
                        Function.identity(),
                        (x, y) -> y
                ));
    }

    private final String day;

    public static DayOfWeek byName(String dayName) {
        return days.get(dayName);
    }

    @Override
    public String toString() {
        return day;
    }
}
