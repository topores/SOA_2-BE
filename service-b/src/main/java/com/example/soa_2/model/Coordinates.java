package com.example.soa_2.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "coordinates")
public class Coordinates extends IdableClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float x;
    private Long y;
}
