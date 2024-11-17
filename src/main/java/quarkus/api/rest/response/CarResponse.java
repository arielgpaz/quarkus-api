package quarkus.api.rest.response;

import java.util.UUID;

public record CarResponse(
        UUID id,
        String name,
        String model,
        String color,
        String engine,
        String year,
        String transmission
) {}

