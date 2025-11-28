package net.datasa.web08.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false,length = 30)
    String id;

    @Column
    String password;

    @Column
    String name;

    @Column
    String phone;


}

