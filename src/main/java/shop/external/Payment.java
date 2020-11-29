package shop.external;

public class Payment {

    private Long id;
    private String Status;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(String status) { this.Status = status;}

}
