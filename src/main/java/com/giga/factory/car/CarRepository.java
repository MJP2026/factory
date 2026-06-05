package com.giga.factory.car;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CarRepository {

    private final Map<Long, Car> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public List<Car> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Car save(Car car) {
        if (car.getId() == null) {
            car.setId(idSequence.getAndIncrement());
        }
        store.put(car.getId(), car);
        return car;
    }

    public boolean existsById(Long id) {
        return store.containsKey(id);
    }

    public void deleteById(Long id) {
        store.remove(id);
    }
}
