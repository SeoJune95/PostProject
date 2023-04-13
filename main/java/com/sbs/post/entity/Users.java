package com.sbs.post.entity;

import jakarta.persistence.*;
import lombok.*;

//@EqualsAndHashCode(of = {"userId","userName",})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId"})})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String userName;

    @Column
    private String userPassword;

    @Column
    private String userEmail;
}
