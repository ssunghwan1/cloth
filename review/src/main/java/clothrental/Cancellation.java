package clothrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Cancellation_table")
public class Cancellation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        ReviewCanceled reviewCanceled = new ReviewCanceled();
        BeanUtils.copyProperties(this, reviewCanceled);
        reviewCanceled.publishAfterCommit();


    }

    @PrePersist
    public void onPrePersist(){
        System.out.println("################# cancellation start");

        try {
            Thread.currentThread().sleep((long) (500 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
