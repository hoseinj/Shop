package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A OrderProduct.
 */
@Entity
@Table(name = "order_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "trace", precision = 21, scale = 2)
    private BigDecimal trace;

    @Column(name = "total", precision = 21, scale = 2)
    private BigDecimal total;

    @Column(name = "tax", precision = 21, scale = 2)
    private BigDecimal tax;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "modified_date")
    private String modifiedDate;

    @ManyToOne
    @JsonIgnoreProperties("orderProducts")
    private Product productId;

    @ManyToOne
    @JsonIgnoreProperties("orderProducts")
    private Order orderId;

    @ManyToOne
    @JsonIgnoreProperties("orderProducts")
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties("orderProducts")
    private Order order;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public OrderProduct name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public OrderProduct model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderProduct quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTrace() {
        return trace;
    }

    public OrderProduct trace(BigDecimal trace) {
        this.trace = trace;
        return this;
    }

    public void setTrace(BigDecimal trace) {
        this.trace = trace;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderProduct total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public OrderProduct tax(BigDecimal tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public OrderProduct isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public OrderProduct createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public OrderProduct modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public OrderProduct createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public OrderProduct modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Product getProductId() {
        return productId;
    }

    public OrderProduct productId(Product product) {
        this.productId = product;
        return this;
    }

    public void setProductId(Product product) {
        this.productId = product;
    }

    public Order getOrderId() {
        return orderId;
    }

    public OrderProduct orderId(Order order) {
        this.orderId = order;
        return this;
    }

    public void setOrderId(Order order) {
        this.orderId = order;
    }

    public Product getProduct() {
        return product;
    }

    public OrderProduct product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public OrderProduct order(Order order) {
        this.order = order;
        return this;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderProduct)) {
            return false;
        }
        return id != null && id.equals(((OrderProduct) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", quantity=" + getQuantity() +
            ", trace=" + getTrace() +
            ", total=" + getTotal() +
            ", tax=" + getTax() +
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
