package pl.edu.agh.iiet.se.controller;

import pl.edu.agh.iiet.se.dto.KBAnswer;
import pl.edu.agh.iiet.se.dto.KBParameter;
import pl.edu.agh.iiet.se.service.KnowledgeBaseService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


@Path("kb")
public class KnowledgeBase {
    @Context
    private ServletContext context;

    private KnowledgeBaseService kbService = new KnowledgeBaseService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("parameters")
    public Response parameters() {
        return Response.ok(kbService.parameters()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json")
    @Path("query")
    public Response getAnswers(@Context HttpServletResponse servletResponse,
                               @NotNull List<KBParameter> parameters) throws MalformedURLException {

        List<String> matchingCars = kbService.matchingCars(parameters);
        List<KBAnswer> answers = new ArrayList<>(matchingCars.size());
        for (String carName : matchingCars) {
            answers.add(new KBAnswer(carName, "/resources/img/" + carName + ".jpg"));
        }
        Response response;
        if (answers.isEmpty()) {
            response = Response.noContent().build();
        } else {
            response = Response.ok(answers).build();
        }

        return response;
    }
}
