package shop;

import org.hibernate.cfg.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import shop.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
@Service
public class PolicyHandler{

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_DeliveryPol(@Payload Ordered ordered){
        if(ordered.isMe())
        {

        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled(@Payload OrderCanceled orderCanceled){
        if(orderCanceled.isMe())
        {
            Iterator<Order> iterator = orderRepository.findAll().iterator();
            while(iterator.hasNext()){
                Order orderTmp = iterator.next();
                if(orderTmp.getProductId() == orderCanceled.getProductId())
                {
                    Optional<Order> Optional = orderRepository.findById(orderTmp.getProductId());
                    Order order = Optional.get();
                    order.setOrderStatus(orderCanceled.getOrderStatus());
                    orderRepository.save(order);
                }
            }
        }
    }


}
