package com.tua.wanchalerm.notification.service;

import com.tua.wanchalerm.notification.model.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sent(Email email) throws MessagingException {
        var ctx = new Context();
        email.getContent().forEach(ctx::setVariable);

        val content = templateEngine.process(email.getTemplate().getFileName(), ctx);
        val mimeMessage = mailSender.createMimeMessage();
        val message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        message.setSubject(email.getSubject());
        message.setTo(email.getTo());

        if(!ObjectUtils.isEmpty(email.getCc())) {
            message.setCc(email.getCc());
        }

        if(!ObjectUtils.isEmpty(email.getBcc())) {
            message.setBcc(email.getBcc());
        }

        message.setText(content, true);

        mailSender.send(mimeMessage);

    }
}
