package quarkus.api.rest;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import quarkus.api.entity.CarEntity;
import quarkus.api.rest.request.CarRequest;
import quarkus.api.service.CarService;

import java.util.UUID;

@Path("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @POST
    public Response createCar(CarRequest carRequest) {
        var car = carService.createCar(carRequest);
        return Response.status(Response.Status.CREATED).entity(car.toResponse()).build();
    }

    @GET
    public Response getAllCars(@QueryParam("page") @DefaultValue("0") Integer page,
                               @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var carList = carService.getAllCars(page, pageSize);
        return Response.status(Response.Status.OK)
                .entity(carList.stream()
                        .map(CarEntity::toResponse)
                ).build();
    }

    @GET
    @Path("/{id}")
    public Response getCarById(@PathParam("id") UUID id) {
        var car = carService.getCar(id);
        return Response.ok(car.toResponse()).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCar(@PathParam("id") UUID id, CarRequest carRequest) {
        var car = carService.update(id, carRequest);
        return Response.ok(car.toResponse()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") UUID id) {
        carService.delete(id);
        return Response.noContent().build();
    }
}
