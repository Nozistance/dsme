package io.nozistance.dsme.entity;

import io.nozistance.dsme.util.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;
    private String category;
    private String composition;
    private String weight;
    private String calories;
    private String price;
    private String image;

    @Column(name = "day_of_week", nullable = false)
    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "item_days", joinColumns = @JoinColumn(name = "item_id"))
    private Set<DayOfWeek> daysOfWeek = EnumSet.noneOf(DayOfWeek.class);
}
