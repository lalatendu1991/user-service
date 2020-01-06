package com.Lalatendu.User.userservice.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserUpdateRequest {

    private Long id;

    private Integer pinCode;

    private String birthDate;
}
