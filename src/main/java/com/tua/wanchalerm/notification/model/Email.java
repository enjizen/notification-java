package com.tua.wanchalerm.notification.model;

import com.tua.wanchalerm.notification.constant.EmailTemplate;
import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Builder
@Data
public class Email {
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private Map<String, String> content;
    private EmailTemplate template;
}
