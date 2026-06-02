package com.edutask.domain.repository;

import com.edutask.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByFirebaseUid(String firebaseUid);
    User save(User user);
}