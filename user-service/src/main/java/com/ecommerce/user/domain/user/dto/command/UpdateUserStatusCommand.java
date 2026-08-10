package com.ecommerce.user.domain.user.dto.command;


import com.ecommerce.user.domain.user.dto.request.UpdateUserStatusRequest;
import com.ecommerce.user.common.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserStatusCommand {
    private UserStatus status;

    public static UpdateUserStatusCommand from(UpdateUserStatusRequest request) {
        return new UpdateUserStatusCommand(request.getStatus());
    }
}