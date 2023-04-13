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
@Table(name = "rooms_has_order")
public class RoomsHasOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany
    @JoinColumn(name = "rooms_id")
    private List<Rooms> roomsList;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}