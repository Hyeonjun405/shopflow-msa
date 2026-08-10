package com.ecommerce.user.domain.user.dto.command;

import com.ecommerce.user.domain.user.dto.request.UpdateUserRoleRequest;
import com.ecommerce.user.common.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRoleCommand {
    private UserRole role;

    public static UpdateUserRoleCommand from(UpdateUserRoleRequest request) {
        return new UpdateUserRoleCommand(request.getRole());
    }
}