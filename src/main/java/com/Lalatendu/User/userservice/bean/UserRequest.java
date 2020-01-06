package com.Lalatendu.User.userservice.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {

    private String fName;

    private String lName;

    private String email;

    private Integer pinCode;

    private String birthDate;


}
