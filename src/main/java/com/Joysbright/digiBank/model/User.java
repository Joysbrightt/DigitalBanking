package com.Joysbright.digiBank.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    private int userId;
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
    @NonNull
    @NotBlank
    private  String password;

    private Account account;
}
