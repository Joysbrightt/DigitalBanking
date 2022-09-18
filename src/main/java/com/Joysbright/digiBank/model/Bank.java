package com.Joysbright.digiBank.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @NotBlank
    @NonNull
    private String bankName;

    private List<User> users;
}
