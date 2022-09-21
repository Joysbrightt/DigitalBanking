package com.Joysbright.digiBank.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    private String message;
    private int StatusCode;
    private boolean isSuccess;
}
