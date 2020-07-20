package com.msheviakou.bundlejwt.authentication.api;

import com.msheviakou.bundlejwt.authentication.api.payload.AuthRequest;
import com.msheviakou.bundlejwt.authentication.api.payload.RefreshTokenRequest;
import com.msheviakou.bundlejwt.authentication.api.payload.SignUpRequest;
import com.msheviakou.bundlejwt.authentication.jwt.JwtUser;
import com.msheviakou.bundlejwt.authentication.token.Token;
import com.msheviakou.bundlejwt.authentication.token.TokenService;
import com.msheviakou.bundlejwt.exception.ResourceNotFoundException;
import com.msheviakou.bundlejwt.user.User;
import com.msheviakou.bundlejwt.user.UserMapper;
import com.msheviakou.bundlejwt.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;
    private final UserMapper userMapper;

    Token login(AuthRequest authRequest) {
        try {
            Authentication authentication = createAuthentication(authRequest);
            JwtUser jwtUser = (JwtUser) authenticationManager.authenticate(authentication).getPrincipal();
            User user = jwtUser.getUser();
            return tokenService.createToken(user);
        } catch (AuthenticationException e) {
            throw new ResourceNotFoundException("Incorrect username or password", FORBIDDEN);
        }
    }

    Token signUp(SignUpRequest signUpRequest) {
        User user = userService.save(userMapper.fromSignUpRequest(signUpRequest));
        return tokenService.createToken(user);
    }

    Token refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String email = tokenService.getEmailFromRefreshToken(refreshTokenRequest.getToken().getRefreshToken());
        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with [email=" + email + "]"));
        return tokenService.createToken(user);
    }

    private Authentication createAuthentication(AuthRequest authRequest) {
        return new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
    }
}
