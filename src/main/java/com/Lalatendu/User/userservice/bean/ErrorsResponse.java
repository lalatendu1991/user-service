package com.Lalatendu.User.userservice.bean;

import lombok.*;

@Getter
@Setter
@Builder
@Data
public class ErrorsResponse {

    private Integer code;

    private String field;

    private String message;
}
