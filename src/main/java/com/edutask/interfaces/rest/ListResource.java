package com.edutask.interfaces.rest;

import com.edutask.application.usecase.list.*;
import com.edutask.interfaces.rest.dto.TaskListDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/api/lists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListResource {

    @Inject GetListsUseCase getListsUseCase;
    @Inject CreateListUseCase createListUseCase;
    @Inject UpdateListUseCase updateListUseCase;
    @Inject DeleteListUseCase deleteListUseCase;

    @GET
    @RolesAllowed("**")
    public Response getLists(@Context SecurityContext ctx) {
        String userId = ctx.getUserPrincipal().getName();
        return Response.ok(getListsUseCase.execute(userId)).build();
    }

    @POST
    @RolesAllowed("**")
    public Response createList(TaskListDto dto, @Context SecurityContext ctx) {
        String userId = ctx.getUserPrincipal().getName();
        var input = new CreateListUseCase.Input(
                dto.title, dto.description, dto.accentColor, dto.icon, userId
        );
        return Response.status(Response.Status.CREATED)
                .entity(createListUseCase.execute(input)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("**")
    public Response updateList(@PathParam("id") Long id, TaskListDto dto) {
        var input = new UpdateListUseCase.Input(
                id, dto.title, dto.description, dto.accentColor, dto.icon
        );
        return Response.ok(updateListUseCase.execute(input)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("**")
    public Response deleteList(@PathParam("id") Long id) {
        deleteListUseCase.execute(id);
        return Response.noContent().build();
    }
}