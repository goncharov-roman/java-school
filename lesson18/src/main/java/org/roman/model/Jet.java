package org.roman.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Jet {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Double length;

    @Column(name = "cruising_speed")
    private Double cruisingSpeed;

    @Column(name = "max_altitude")
    private Integer maxAltitude;

    @Column(name = "passenger_count")
    private Integer passengerCapacity;
}
