package quarkus.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import quarkus.api.entity.CarEntity;
import quarkus.api.exception.CarNotFoundException;
import quarkus.api.rest.request.CarRequest;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CarService {

    @Transactional
    public CarEntity createCar(CarRequest car) {
        var carEntity = car.toDomain();
        CarEntity.persist(carEntity);
        return carEntity;
    }

    public List<CarEntity> getAllCars(Integer page, Integer pageSize) {
        return CarEntity.findAll()
                .page(page, pageSize)
                .list();
    }

    public CarEntity getCar(UUID id) {
        return (CarEntity) CarEntity.findByIdOptional(id).orElseThrow(
                CarNotFoundException::new
        );
    }

    @Transactional
    public CarEntity update(UUID id, CarRequest carRequest) {
        var carEntity = getCar(id);

        carEntity.name = carRequest.name();
        carEntity.model = carRequest.model();
        carEntity.color = carRequest.color();
        carEntity.engine = carRequest.engine();
        carEntity.year = carRequest.year();
        carEntity.transmission = carRequest.transmission();

        CarEntity.persist(carEntity);

        return carEntity;
    }

    @Transactional
    public void delete(UUID id) {
        var carEntity = getCar(id);
        CarEntity.deleteById(carEntity.id);
    }
}
