package shop;

public class OrderCanceled extends AbstractEvent {

    private Long id;
    private Long productId;
    private Long qty;
    private String orderStatus;

    public OrderCanceled(){
        super();
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

    public String getOrderStatus() {return orderStatus;}
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
