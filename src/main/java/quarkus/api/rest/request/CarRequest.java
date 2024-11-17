package quarkus.api.rest.request;

import quarkus.api.entity.CarEntity;

public record CarRequest(
    String name,
    String model,
    String color,
    String engine,
    String year,
    String transmission
) {
    public CarEntity toDomain() {
        return new CarEntity(name, model, color, engine, year, transmission);
    }
}
