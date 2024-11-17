package quarkus.api.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CarNotFoundExceptionHandler implements ExceptionMapper<CarNotFoundException> {

    @Override
    public Response toResponse(CarNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Car not found.").build();
    }
}
