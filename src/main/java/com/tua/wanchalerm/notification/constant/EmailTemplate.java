package com.tua.wanchalerm.notification.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EmailTemplate {
    OTP("otp", "otp.html");

    private final String templateName;
    private final String fileName;
}
