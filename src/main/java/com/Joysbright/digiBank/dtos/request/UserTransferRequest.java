package com.Joysbright.digiBank.dtos.request;

import lombok.*;

import javax.swing.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTransferRequest {
    private  String senderAccountNo;
    private String senderAccountPassword;
    private String ReceiverAccountNo;
    private BigDecimal amountToSend;
    private String narration;
}
