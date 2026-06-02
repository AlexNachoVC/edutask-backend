package com.edutask.infrastructure.persistence.repository;

import com.edutask.domain.model.User;
import com.edutask.domain.repository.UserRepository;
import com.edutask.infrastructure.persistence.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByFirebaseUid(String firebaseUid) {
        return UserEntity.findByFirebaseUid(firebaseUid)
                .map(this::toDomain);
    }

    @Override
    @Transactional
    public User save(User user) {
        UserEntity entity = new UserEntity();
        entity.firebaseUid = user.getFirebaseUid();
        entity.email = user.getEmail();
        entity.name = user.getName();
        entity.persist();
        return toDomain(entity);
    }

    private User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(String.valueOf(entity.id));
        user.setEmail(entity.email);
        user.setName(entity.name);
        user.setFirebaseUid(entity.firebaseUid);
        return user;
    }
}