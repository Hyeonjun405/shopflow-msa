package com.ecommerce.user.domain.user.dto;


import com.ecommerce.user.domain.user.entity.User;
import com.ecommerce.user.common.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private UserRole role;

    public static UserInfo from(User user) {
        return new UserInfo(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getRole()
        );
    }
}