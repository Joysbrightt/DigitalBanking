package com.Joysbright.digiBank.dtos.request;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RegisterUserRequest { private int userId;
    @NonNull
    @NotBlank
    private String firstName;
    @NonNull
    @NotBlank
    private String lastName;
    @NonNull
    @NotBlank
    @Email
    private  String email;
    @NonNull
    @NotBlank
    private  String phoneNo;
    private double initialDeposit;
    private String password;



}
