package io.nozistance.dsme.model;

import io.nozistance.dsme.util.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;
    private String category;
    private String ingredients;
    private String weight;
    private String calories;
    private String price;
    private String image;

    @CollectionTable
    @Singular("dayOfWeek")
    @Column(nullable = false)
    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.EAGER)
    private Set<DayOfWeek> daysOfWeek;
}
