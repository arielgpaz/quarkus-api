package quarkus.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import quarkus.api.rest.response.CarResponse;

import java.util.UUID;

@Entity
@Table(name = "tb_car")
public class CarEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String name;
    public String model;
    public String color;
    public String engine;
    public String year;
    public String transmission;

    public CarEntity(String name, String model, String color, String engine, String year, String transmission) {
        this.name = name;
        this.model = model;
        this.color = color;
        this.engine = engine;
        this.year = year;
        this.transmission = transmission;
    }

    public CarEntity() {
    }

    public CarResponse toResponse() {
        return new CarResponse(id, name, model, color, engine, year, transmission);
    }
}
