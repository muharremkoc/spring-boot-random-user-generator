package com.example.springbootrandomusergenerator.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GENERATED_USERS")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    String id;


    String firstName;

    String phone;

    String country;

    String userName;

    String email;
}
