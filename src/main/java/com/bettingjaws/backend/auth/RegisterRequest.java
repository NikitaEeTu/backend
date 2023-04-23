package com.bettingjaws.backend.auth;

import com.bettingjaws.backend.persistence.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String country;
    private Integer gameLevel;
    private Integer xpNumber;
    private Role role;
    private String password;
}
