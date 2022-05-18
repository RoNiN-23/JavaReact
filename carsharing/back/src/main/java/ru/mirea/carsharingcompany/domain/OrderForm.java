package ru.mirea.carsharingcompany.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderForm {
    private long userId;
    private String carMark;
    private String carNumber;
    private String startDataAndTime;
    private String endDataAndTime;

    public Order toOrder(User user, Car car){
        Date date = new Date();
        Order order = new Order();
        order.setUserId(user.getId());
        order.setCarMark(car.getMark());
        order.setCarNumber(car.getNumber());
        order.setStartDataAndTime(date.toString());
        return order;
    }
}