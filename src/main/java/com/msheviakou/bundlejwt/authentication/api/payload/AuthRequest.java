package com.msheviakou.bundlejwt.authentication.api.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
