package com.demo.employee.service.repository;

import jakarta.mail.MessagingException;

public interface EmailSenderRepo {
    void sendSimpleEmail(String toEmail, String body, String subject) throws MessagingException;


}
