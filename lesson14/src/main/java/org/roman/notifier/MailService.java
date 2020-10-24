package org.roman.notifier;

import javax.mail.MessagingException;

public interface MailService {

    void sendMail(String resultingHtml, String recipients) throws MessagingException;
}
