package clothrental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Review_table")
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String status;
    private String content;

    @PostPersist
    public void onPostPersist(){
        Shipped shipped = new Shipped();
        BeanUtils.copyProperties(this, shipped);
        shipped.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("################# Review Status Updated!!");
        Reviewed reviewed = new Reviewed();
        BeanUtils.copyProperties(this, reviewed);
        reviewed.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        clothrental.external.Reviewed review = new clothrental.external.Reviewed();
        // mappings goes here
        // 아래 this는 Order 어그리게이트
        review.setId(this.getOrderId());
        review.setOrderId(this.getOrderId());
        review.setStatus("review Finish");
        ReviewApplication.applicationContext.getBean(clothrental.external.ReviewedService.class)
                .reviewship(review);
        System.out.println("################# Review Status Updated!!!!!");

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
