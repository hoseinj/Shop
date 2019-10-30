package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A OrderTotal.
 */
@Entity
@Table(name = "order_total")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", precision = 21, scale = 2)
    private BigDecimal value;

    @ManyToOne
    @JsonIgnoreProperties("orderTotals")
    private Order orderId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public OrderTotal value(BigDecimal value) {
        this.value = value;
        return this;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Order getOrderId() {
        return orderId;
    }

    public OrderTotal orderId(Order order) {
        this.orderId = order;
        return this;
    }

    public void setOrderId(Order order) {
        this.orderId = order;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderTotal)) {
            return false;
        }
        return id != null && id.equals(((OrderTotal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderTotal{" +
            "id=" + getId() +
            ", value=" + getValue() +
            "}";
    }
}
