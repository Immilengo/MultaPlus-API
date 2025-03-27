package ao.multaplus.sms.service;

import ao.it.mimo.api.exception.MimoException;
import org.springframework.web.client.RestClientException;

public interface SMSService {
    /**
     * Send SMS to a phone number
     *
     * @param to      the phone number to send the SMS to
     * @param message the message to send
     * @throws MimoException if an error occurs while sending the SMS
     */
    void sendSMS(String to, String message) throws MimoException;

    /**
     * Send SMS to a phone number using Mimo Rest API
     *
     * @param to      the phone number to send the SMS to
     * @param message the message to send
     * @throws RestClientException if an error occurs while sending the SMS
     */
    void sendSMSWithMimoRestAip(String to, String message) throws RestClientException;
}
