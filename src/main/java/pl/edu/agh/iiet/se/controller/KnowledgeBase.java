package pl.edu.agh.iiet.se.controller;

import pl.edu.agh.iiet.se.dto.KBAnswer;
import pl.edu.agh.iiet.se.dto.KBParameters;
import pl.edu.agh.iiet.se.service.KnowledgeBaseService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Path("kb")
public class KnowledgeBase {
    @Context
    private ServletContext context;

    private KnowledgeBaseService kbService = new KnowledgeBaseService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/json")
    public Response getAnswers(@Context HttpServletResponse servletResponse,
                              @Valid KBParameters parameters) throws MalformedURLException {

        List<String> matchingCars = kbService.matchingCars(parameters);
        List<KBAnswer> answers = new ArrayList<KBAnswer>(matchingCars.size());
        for(String carName: matchingCars) {
            answers.add(new KBAnswer(carName, "/resources/img/" + carName));
        }
        Response response = null;

        if(answers.isEmpty()) {
            response = Response.noContent().build();
        } else {
            response = Response.ok(answers).build();
        }

        return response;
    }
}
