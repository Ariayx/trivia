package com.trivia.trivia.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uuid")
    private UUID uuid;

    public UserEntity() {
    }

    public UserEntity(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
