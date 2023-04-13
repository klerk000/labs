package ua.com.project.entity;


import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date_created;
    private String payment;
    private long timeOfStay;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}