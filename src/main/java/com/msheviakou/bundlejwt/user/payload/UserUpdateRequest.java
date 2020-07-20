package com.msheviakou.bundlejwt.user.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserUpdateRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    private String email;

    private String status;
}
