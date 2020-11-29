package shop;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import shop.external.Payment;

import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Long qty;
    private String orderStatus;
    private Long orderId;

    @PostPersist
    public void onPostPersist()
    {
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.setOrderStatus("Order");
        ordered.setOrderId(this.getOrderId());
        ordered.publishAfterCommit();

        shop.external.Payment payment = new shop.external.Payment();
        System.out.println("결제 이벤트 발생");
        payment.setId(this.getOrderId());
        payment.setStatus("Paid");
        OrderApplication.applicationContext.getBean(shop.external.PaymentService.class)
                .pay(payment);

    }
    @PreUpdate
    public void onPrePersist(){


        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        if(orderCanceled.getOrderStatus().equals("Cancel"))
        {
            orderCanceled.setOrderStatus(this.getOrderStatus());
            orderCanceled.publishAfterCommit();
        }
        else
        {
            PaymentRequested paymentRequested = new PaymentRequested();
            BeanUtils.copyProperties(this, paymentRequested);
            paymentRequested.publishAfterCommit();



            //orderCanceled.publishAfterCommit();

            //Following code causes dependency to external APIs
            // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

            //shop.external.Cancellation cancellation = new shop.external.Cancellation();
            // mappings goes here

            //OrderApplication.applicationContext.getBean(shop.external.CancellationService.class)
            //    .cancel(cancellation);
        }



    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public void setOrderStatus(String orderStatus){this.orderStatus = orderStatus;}
    public String getOrderStatus(){return orderStatus;}

    public Long getOrderId(){return orderId;}
    public void setOrderId(Long orderId){this.orderId = orderId;}
}
