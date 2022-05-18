package ru.mirea.carsharingcompany.repo;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.carsharingcompany.domain.Car;

public interface CarRepo extends CrudRepository<Car,Long> {
    Car findCarById(Long id);
    Iterable<Car> findAllByReserved(boolean reserved);
    Car findFirstByMarkAndReserved(String mark, boolean reserved);
}
