package ru.mirea.carsharingcompany.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mark;
    private String number;
    private boolean reserved;
    private int carPrice;
}
