package clothrental;

import clothrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Ship(@Payload Ordered ordered){

        if(ordered.isMe()){
            // To-Do : SMS발송, CJ Logistics 연계, ...
            Delivery delivery = new Delivery();
            delivery.setOrderId(ordered.getId());
            delivery.setStatus("Delivery Started");

            deliveryRepository.save(delivery);

            System.out.println("##### listener Ship : " + ordered.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Ship(@Payload Returned returned){

        if(returned.isMe()){
            // To-Do : SMS발송, CJ Logistics 연계, ...
            Delivery delivery = new Delivery();
            delivery.setOrderId(returned.getId());
            delivery.setStatus("Return Started");

            deliveryRepository.save(delivery);

            System.out.println("##### listener Ship : " + returned.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_Returnship(@Payload Returned returned){

        if(returned.isMe()){
            System.out.println("##### listener Returnship : " + returned.toJson());
        }
    }

}
