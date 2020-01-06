package com.Lalatendu.User.userservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_service")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "PinCode")
    private Integer pinCode;

    @Column(name = "Birth_Date")
    private LocalDate birthDate;

    @Column(name = "Status")
    private String status;

}
