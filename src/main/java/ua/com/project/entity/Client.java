package ua.com.project.entity;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String surname;
    private String lastname;
    private int age;
    private int phone;
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Order> order;
}