package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.OrderTotal} entity.
 */
public class OrderTotalDTO implements Serializable {

    private Long id;

    private BigDecimal value;


    private Long orderIdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getOrderIdId() {
        return orderIdId;
    }

    public void setOrderIdId(Long orderId) {
        this.orderIdId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderTotalDTO orderTotalDTO = (OrderTotalDTO) o;
        if (orderTotalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderTotalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderTotalDTO{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", orderId=" + getOrderIdId() +
            "}";
    }
}
