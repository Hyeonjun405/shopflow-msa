package com.ecommerce.user.domain.user.dto.request;


import com.ecommerce.user.common.enums.UserStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserStatusRequest {

    @NotNull(message = "상태는 필수입니다")
    private UserStatus status;
}
