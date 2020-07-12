package com.tua.wanchalerm.notification.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
public class EmailRequest {
    @JsonProperty("to")
    private String[] to;

    @JsonProperty("cc")
    private String[] cc;

    @JsonProperty("bcc")
    private String[] bcc;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("content")
    private Map<String, String> content;
}
