package com.edutask.interfaces.rest;

import com.edutask.application.usecase.task.*;
import com.edutask.interfaces.rest.dto.TaskDto;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/lists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @Inject GetTasksUseCase getTasksUseCase;
    @Inject CreateTaskUseCase createTaskUseCase;
    @Inject UpdateTaskUseCase updateTaskUseCase;
    @Inject DeleteTaskUseCase deleteTaskUseCase;

    @GET
    @Path("/{listId}/tasks")
    @RolesAllowed("**")
    public Response getTasks(@PathParam("listId") Long listId) {
        return Response.ok(getTasksUseCase.execute(listId)).build();
    }

    @POST
    @Path("/{listId}/tasks")
    @RolesAllowed("**")
    public Response createTask(@PathParam("listId") Long listId, TaskDto dto) {
        var input = new CreateTaskUseCase.Input(
                dto.title, dto.description, dto.dueDate, dto.priority, listId
        );
        return Response.status(Response.Status.CREATED)
                .entity(createTaskUseCase.execute(input)).build();
    }

    @PUT
    @Path("/{listId}/tasks/{id}")
    @RolesAllowed("**")
    public Response updateTask(@PathParam("listId") Long listId,
                               @PathParam("id") Long id, TaskDto dto) {
        var input = new UpdateTaskUseCase.Input(
                id, dto.title, dto.description, dto.completed, dto.dueDate, dto.priority
        );
        return Response.ok(updateTaskUseCase.execute(input)).build();
    }

    @DELETE
    @Path("/{listId}/tasks/{id}")
    @RolesAllowed("**")
    public Response deleteTask(@PathParam("listId") Long listId,
                               @PathParam("id") Long id) {
        deleteTaskUseCase.execute(id);
        return Response.noContent().build();
    }
}