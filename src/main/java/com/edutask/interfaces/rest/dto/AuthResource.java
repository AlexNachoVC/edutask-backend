package com.edutask.interfaces.rest;

import com.edutask.application.usecase.auth.VerifyFirebaseTokenUseCase;
import com.edutask.interfaces.rest.dto.AuthResponse;
import com.edutask.interfaces.rest.dto.LoginRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    VerifyFirebaseTokenUseCase verifyFirebaseTokenUseCase;

    @POST
    @Path("/verify")
    public Response verify(LoginRequest request) {
        try {
            var result = verifyFirebaseTokenUseCase.execute(request.idToken);

            AuthResponse response = new AuthResponse();
            response.token = result.token();
            response.user = new AuthResponse.UserDto();
            response.user.id = result.user().getId();
            response.user.email = result.user().getEmail();
            response.user.name = result.user().getName();

            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\": \"Token inválido\"}")
                    .build();
        }
    }
}