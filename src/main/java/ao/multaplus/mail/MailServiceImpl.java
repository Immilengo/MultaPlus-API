package ao.multaplus.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(String to, String subject, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setText(text, true);
            helper.setSubject(subject);
            helper.setTo(to);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("An error occur while send a message");
        }
    }

    @Override
    public void sendOTP(String email, Integer code, Long expirationTime) {
        Context context = new Context();
        context.setVariable("code", code);
        context.setVariable("expirationTime", expirationTime / 60);
        String body = templateEngine.process("mail/otp", context);
        sendMail(email, "Seu código de verificação", body);

    }
}
