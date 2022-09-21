package com.Joysbright.digiBank.dtos.request;

import lombok.*;

import javax.swing.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTransferRequest {
    private  String senderAccountNo;
    private String senderAccountPassword;
    private String ReceiverAccountNo;
    private double amountToSend;
    private String narration;
}
