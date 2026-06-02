package com.edutask.application.usecase.auth;

import com.edutask.domain.model.User;
import com.edutask.domain.repository.UserRepository;
import com.edutask.infrastructure.firebase.FirebaseAdminService;
import com.google.firebase.auth.FirebaseToken;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;

@ApplicationScoped
public class VerifyFirebaseTokenUseCase {

    @Inject
    FirebaseAdminService firebaseAdminService;

    @Inject
    UserRepository userRepository;

    public record Result(String token, User user) {}

    public Result execute(String idToken) {
        // 1. Verificar token de Firebase
        FirebaseToken firebaseToken = firebaseAdminService.verifyIdToken(idToken);

        // 2. Buscar o crear usuario en nuestra DB
        User user = userRepository.findByFirebaseUid(firebaseToken.getUid())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setFirebaseUid(firebaseToken.getUid());
                    newUser.setEmail(firebaseToken.getEmail());
                    newUser.setName(firebaseToken.getName() != null
                            ? firebaseToken.getName()
                            : firebaseToken.getEmail());
                    return userRepository.save(newUser);
                });

        // 3. Generar JWT propio
        String jwt = Jwt.issuer("edutask")
                .subject(user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .expiresIn(Duration.ofDays(7))
                .sign();

        return new Result(jwt, user);
    }
}