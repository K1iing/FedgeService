package Fedge.PaymentService.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class OrdersConsumer {

    @SqsListener("myQueue")
    public void listen(String message) {
        System.out.println("Mensagem recebida: " + message);
    }
}
