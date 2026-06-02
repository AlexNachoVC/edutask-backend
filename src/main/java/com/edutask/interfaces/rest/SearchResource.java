package com.edutask.interfaces.rest;

import com.edutask.domain.repository.TaskListRepository;
import com.edutask.domain.repository.TaskRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.util.Map;

@Path("/api/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {

    @Inject TaskListRepository taskListRepository;
    @Inject TaskRepository taskRepository;

    @GET
    @RolesAllowed("**")
    public Response search(@QueryParam("q") String query,
                           @Context SecurityContext ctx) {
        if (query == null || query.isBlank()) {
            return Response.ok(Map.of("lists", java.util.List.of(),
                    "tasks", java.util.List.of())).build();
        }
        String userId = ctx.getUserPrincipal().getName();
        var lists = taskListRepository.searchByTitle(query, userId);
        var tasks = taskRepository.searchByTitle(query, userId);
        return Response.ok(Map.of("lists", lists, "tasks", tasks)).build();
    }
}