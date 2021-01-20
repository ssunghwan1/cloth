package clothrental;

import clothrental.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    ReviewRepository reviewRepository;

//    @StreamListener(KafkaProcessor.INPUT)
//    public void wheneverOrdered_Ship(@Payload Reviewed reviewed){
//        System.out.println("##### review process111111 : " + reviewed.toJson());
//        if(reviewed.isMe()){
//            // To-Do : SMS발송, CJ Logistics 연계, ...
//            Review review = new Review();
//            review.setOrderId(reviewed.getId());
//            review.setContent(reviewed.getContent());
//            review.setStatus("reviewed");
//
//            reviewRepository.save(review);
//
//            System.out.println("##### review process22222 : " + reviewed.toJson());
//        }
//    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Ship(@Payload Ordered ordered){
        System.out.println("##### listener Review req!!!! : " + ordered.toJson());
        if(ordered.isMe()){
            // 리뷰 요청
            Review review = new Review();
            review.setOrderId(ordered.getId());
            review.setStatus("Request");

            reviewRepository.save(review);

            System.out.println("##### listener Review req : " + ordered.toJson());
        }
    }

//    @StreamListener(KafkaProcessor.INPUT)
//    public void wheneverShipped_UpdateStatus(@Payload Shipped shipped){
//
//        if(shipped.isMe()){
//            Optional<Order> orderOptional = orderRepository.findById(shipped.getOrderId());
//            Order order = orderOptional.get();
//            order.setStatus(shipped.getStatus());
//
//            orderRepository.save(order);
//            System.out.println("##### listener UpdateStatus : " + shipped.toJson());
//        }
//    }

}
