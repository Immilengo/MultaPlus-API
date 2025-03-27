package ao.multaplus.mail;

import jakarta.mail.MessagingException;


public interface MailService {
    /**
     * Send mail to the given mail address
     *
     * @param to      mail address to send
     * @param subject mail subject
     * @param text    mail text content
     * @throws MessagingException if mail sending fails
     */
    void sendMail(String to, String subject, String text) throws MessagingException;

    /**
     * Send OTP code to the given mail address
     *
     * @param email          mail address to send the OTP code.
     * @param Code           the OTP code to send.
     * @param expirationTime the expiration time of the OTP code.
     * @throws MessagingException if mail sending fails
     */
    void sendOTP(String email, Integer Code,
                 Long expirationTime) throws MessagingException;

}
