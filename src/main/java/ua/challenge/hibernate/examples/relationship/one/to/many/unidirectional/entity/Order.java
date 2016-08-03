package ua.challenge.hibernate.examples.relationship.one.to.many.unidirectional.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by d.bakal on 8/1/2016.
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ord_lines", joinColumns = @JoinColumn(name = "order_fk"),
    inverseJoinColumns = @JoinColumn(name = "order_line_fk"))
    private List<OrderLine> orderLines;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (creationDate != null ? !creationDate.equals(order.creationDate) : order.creationDate != null) return false;
        return !(orderLines != null ? !orderLines.equals(order.orderLines) : order.orderLines != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (orderLines != null ? orderLines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", orderLines=" + orderLines +
                '}';
    }
}
