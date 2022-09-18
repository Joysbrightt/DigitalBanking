package com.Joysbright.digiBank.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;


import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    private int id;
    @NotBlank
    @NonNull
    private String narration;
    @NotBlank
    @NonNull
    private double amount;
    @NotBlank
    @NonNull
    private Transactiontype transactionType;

    @JsonSerialize()
    @JsonDeserialize()
    private LocalDate localDate;

    private BigDecimal balanceAfterTransaction;
}
