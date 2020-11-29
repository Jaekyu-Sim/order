
package shop;

public class Ordered extends AbstractEvent {

    private Long id;
    private Long productId;
    private Long qty;
    private String orderStatus;
    private Long orderId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId(){return orderId;}
    public void setOrderId(Long orderId){this.orderId = orderId;}

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Long getProductId() {
        return productId;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
    public Long getQty() { return qty;
    }

    public void setOrderStatus(String status) { this.orderStatus = orderStatus;}
    public String getOrderStatus() { return orderStatus;}
}
