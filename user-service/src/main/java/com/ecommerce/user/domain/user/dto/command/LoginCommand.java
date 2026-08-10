package com.ecommerce.user.domain.user.dto.command;

import com.ecommerce.user.domain.user.dto.request.LoginRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginCommand {
    private String email;
    private String password;

    public static LoginCommand from(LoginRequest request) {
        return new LoginCommand(
                request.getEmail(),
                request.getPassword()
        );
    }
}