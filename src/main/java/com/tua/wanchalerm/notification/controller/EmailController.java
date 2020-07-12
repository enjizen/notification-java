package com.tua.wanchalerm.notification.controller;

import com.tua.wanchalerm.notification.constant.EmailTemplate;
import com.tua.wanchalerm.notification.constant.ResponseConstant;
import com.tua.wanchalerm.notification.controller.request.EmailRequest;
import com.tua.wanchalerm.notification.factory.ResponseFactory;
import com.tua.wanchalerm.notification.model.Email;
import com.tua.wanchalerm.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

import static com.tua.wanchalerm.notification.constant.ResponseConstant.GENERAL_ERROR_CODE;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final ResponseFactory responseFactory;

    @PostMapping("/v1/email")
    public ResponseEntity sendEmail(@RequestParam("template") EmailTemplate template, @RequestBody EmailRequest request) {


        log.info("Start send email to {} and template type [{}]", request.getTo(), template.getTemplateName());

        var email = Email.builder()
                .to(request.getTo())
                .cc(request.getCc())
                .bcc(request.getBcc())
                .subject(request.getSubject())
                .content(request.getContent())
                .template(template)
                .build();

        try {
            emailService.sent(email);
        } catch (MessagingException e) {
            log.error("Send email error", e);
            return responseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, GENERAL_ERROR_CODE);
        }

        log.info("End send email to {} and template type [{}]", request.getTo(), template.getTemplateName());

        return responseFactory.success();
    }
}
