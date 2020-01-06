package com.Lalatendu.User.userservice.bean;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Data
public class UserResponse {

    private String resMsg;

    private Long userId;

    private List<ErrorsResponse> valErrors;

}
