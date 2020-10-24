package org.roman.notifier;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailServiceImpl implements MailService {

    @Override
    public void sendMail(String resultingHtml, String recipients) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("mail.google.com");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);
        helper.setText(resultingHtml, true);
        helper.setSubject("Monthly department salary report");

        mailSender.send(message);
    }
}
