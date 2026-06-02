package com.edutask.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "users")
public class UserEntity extends PanacheEntity {

    @Column(unique = true, nullable = false)
    public String firebaseUid;

    @Column(unique = true, nullable = false)
    public String email;

    public String name;

    public static Optional<UserEntity> findByFirebaseUid(String firebaseUid) {
        return find("firebaseUid", firebaseUid).firstResultOptional();
    }
}