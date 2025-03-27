package ao.multaplus.sms.service;

import ao.it.mimo.api.exception.MimoException;
import ao.it.mimo.api.service.MessageService;
import ao.it.mimo.api.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class SMSServiceImpl implements SMSService {

    private final String senderId;
    private final MessageService messageService;
    private final String token;
    private final String MIMO_URL;

    public SMSServiceImpl(@Qualifier("mimo-token") String token,
                          @Value("${api.security.mimo.sender.id}") String senderId,
                          @Value("${api.mimi.Baseurl}") String MIMO_URL) {
        this.MIMO_URL = MIMO_URL;
        this.senderId = senderId;
        this.token = token;
        this.messageService = ServiceFactory.createMessageService(token);
    }

    @Override
    public void sendSMSWithMimoRestAip(String to, String message) {
        String smsUrl = String.format(
                "%s/message/send?token=%s&sender=%s&recipients=%s&text=%s", MIMO_URL,
                token, senderId, to, message);
        try {
            RestClient.create()
                    .get()
                    .uri(smsUrl)
                    .retrieve()
                    .toBodilessEntity();

        } catch (RestClientException e) {
            System.err.println("Error send message: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error send message", e);
        }
    }

    @Override
    public void sendSMS(String to, String message) {
        try {
            messageService.send(senderId, message, to);
        } catch (MimoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}

