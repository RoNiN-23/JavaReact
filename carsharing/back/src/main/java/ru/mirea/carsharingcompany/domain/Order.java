package ru.mirea.carsharingcompany.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String carMark;
    private String carNumber;
    private String startDataAndTime;
    private String endDataAndTime;
    private int price;

    public int calculPrice(String startDataAndTime, String endDataAndTime, int carPrice) {
        int price = 0;
        int hours = 0;
        int minutes = 0;
        String[] startData = startDataAndTime.split(" ");
        String[] endData = endDataAndTime.split(" ");
        String[] hoursStart = startData[3].split(":");
        String[] hoursEnd = endData[3].split(":");
        if(!startData[2].equals(endData[2])) {
            hours += 23 - Integer.parseInt(hoursStart[0]);
            minutes += 60 - Integer.parseInt(hoursStart[1]);
            hours += Integer.parseInt(hoursEnd[0]);
            minutes += Integer.parseInt(hoursEnd[1]);
            minutes += hours * 60;
        }else {
            hours += Integer.parseInt(hoursEnd[0]) - Integer.parseInt(hoursStart[0]);
            minutes += hours*60;
            minutes += Integer.parseInt(hoursEnd[1]) - Integer.parseInt(hoursStart[1]);
        }
        price = minutes*carPrice;
        return price;
    }
}
