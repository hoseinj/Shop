package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A OrderLog.
 */
@Entity
@Table(name = "order_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "invoce_perfix")
    private String invocePerfix;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "shipping_fristname")
    private String shippingFristname;

    @Column(name = "shipping_lastname")
    private String shippingLastname;

    @Column(name = "shipping_company")
    private String shippingCompany;

    @Column(name = "shipping_address_1")
    private String shippingAddress1;

    @Column(name = "shipping_address_2")
    private String shippingAddress2;

    @Column(name = "shipping_city")
    private String shippingCity;

    @Column(name = "shipping_postcode")
    private String shippingPostcode;

    @Column(name = "shipping_country")
    private String shippingCountry;

    @Column(name = "shipping_zone")
    private String shippingZone;

    @Column(name = "shipping_address_format")
    private String shippingAddressFormat;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "payment_firstname")
    private String paymentFirstname;

    @Column(name = "payment_lastname")
    private String paymentLastname;

    @Column(name = "payment_company")
    private String paymentCompany;

    @Column(name = "payment_address_1")
    private String paymentAddress1;

    @Column(name = "payment_address_2")
    private String paymentAddress2;

    @Column(name = "payment_city")
    private String paymentCity;

    @Column(name = "payment_postcode")
    private String paymentPostcode;

    @Column(name = "payment_country")
    private String paymentCountry;

    @Column(name = "payment_zone")
    private String paymentZone;

    @Column(name = "payment_address_format")
    private String paymentAddressFormat;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "comment")
    private String comment;

    @Column(name = "total", precision = 21, scale = 2)
    private BigDecimal total;

    @Column(name = "reward")
    private Integer reward;

    @Column(name = "commision", precision = 21, scale = 2)
    private BigDecimal commision;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "currency_value", precision = 21, scale = 2)
    private BigDecimal currencyValue;

    @Column(name = "ip")
    private String ip;

    @Column(name = "payment_flag")
    private Integer paymentFlag;

    @Column(name = "order_name")
    private String orderName;

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
    @JsonIgnoreProperties("orderLogs")
    private Customer customerId;

    @ManyToOne
    @JsonIgnoreProperties("orderLogs")
    private Currency currencyId;

    @ManyToOne
    @JsonIgnoreProperties("orderLogs")
    private OrderStatus orderStatusId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public OrderLog invoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvocePerfix() {
        return invocePerfix;
    }

    public OrderLog invocePerfix(String invocePerfix) {
        this.invocePerfix = invocePerfix;
        return this;
    }

    public void setInvocePerfix(String invocePerfix) {
        this.invocePerfix = invocePerfix;
    }

    public String getFirstname() {
        return firstname;
    }

    public OrderLog firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public OrderLog lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public OrderLog email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public OrderLog telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public OrderLog fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getShippingFristname() {
        return shippingFristname;
    }

    public OrderLog shippingFristname(String shippingFristname) {
        this.shippingFristname = shippingFristname;
        return this;
    }

    public void setShippingFristname(String shippingFristname) {
        this.shippingFristname = shippingFristname;
    }

    public String getShippingLastname() {
        return shippingLastname;
    }

    public OrderLog shippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
        return this;
    }

    public void setShippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public OrderLog shippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
        return this;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public OrderLog shippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
        return this;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public OrderLog shippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
        return this;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public OrderLog shippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
        return this;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public OrderLog shippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
        return this;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public OrderLog shippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
        return this;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingZone() {
        return shippingZone;
    }

    public OrderLog shippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
        return this;
    }

    public void setShippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
    }

    public String getShippingAddressFormat() {
        return shippingAddressFormat;
    }

    public OrderLog shippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
        return this;
    }

    public void setShippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public OrderLog shippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentFirstname() {
        return paymentFirstname;
    }

    public OrderLog paymentFirstname(String paymentFirstname) {
        this.paymentFirstname = paymentFirstname;
        return this;
    }

    public void setPaymentFirstname(String paymentFirstname) {
        this.paymentFirstname = paymentFirstname;
    }

    public String getPaymentLastname() {
        return paymentLastname;
    }

    public OrderLog paymentLastname(String paymentLastname) {
        this.paymentLastname = paymentLastname;
        return this;
    }

    public void setPaymentLastname(String paymentLastname) {
        this.paymentLastname = paymentLastname;
    }

    public String getPaymentCompany() {
        return paymentCompany;
    }

    public OrderLog paymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
        return this;
    }

    public void setPaymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    public String getPaymentAddress1() {
        return paymentAddress1;
    }

    public OrderLog paymentAddress1(String paymentAddress1) {
        this.paymentAddress1 = paymentAddress1;
        return this;
    }

    public void setPaymentAddress1(String paymentAddress1) {
        this.paymentAddress1 = paymentAddress1;
    }

    public String getPaymentAddress2() {
        return paymentAddress2;
    }

    public OrderLog paymentAddress2(String paymentAddress2) {
        this.paymentAddress2 = paymentAddress2;
        return this;
    }

    public void setPaymentAddress2(String paymentAddress2) {
        this.paymentAddress2 = paymentAddress2;
    }

    public String getPaymentCity() {
        return paymentCity;
    }

    public OrderLog paymentCity(String paymentCity) {
        this.paymentCity = paymentCity;
        return this;
    }

    public void setPaymentCity(String paymentCity) {
        this.paymentCity = paymentCity;
    }

    public String getPaymentPostcode() {
        return paymentPostcode;
    }

    public OrderLog paymentPostcode(String paymentPostcode) {
        this.paymentPostcode = paymentPostcode;
        return this;
    }

    public void setPaymentPostcode(String paymentPostcode) {
        this.paymentPostcode = paymentPostcode;
    }

    public String getPaymentCountry() {
        return paymentCountry;
    }

    public OrderLog paymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
        return this;
    }

    public void setPaymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
    }

    public String getPaymentZone() {
        return paymentZone;
    }

    public OrderLog paymentZone(String paymentZone) {
        this.paymentZone = paymentZone;
        return this;
    }

    public void setPaymentZone(String paymentZone) {
        this.paymentZone = paymentZone;
    }

    public String getPaymentAddressFormat() {
        return paymentAddressFormat;
    }

    public OrderLog paymentAddressFormat(String paymentAddressFormat) {
        this.paymentAddressFormat = paymentAddressFormat;
        return this;
    }

    public void setPaymentAddressFormat(String paymentAddressFormat) {
        this.paymentAddressFormat = paymentAddressFormat;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public OrderLog paymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public OrderLog comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderLog total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getReward() {
        return reward;
    }

    public OrderLog reward(Integer reward) {
        this.reward = reward;
        return this;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public OrderLog commision(BigDecimal commision) {
        this.commision = commision;
        return this;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public OrderLog currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getCurrencyValue() {
        return currencyValue;
    }

    public OrderLog currencyValue(BigDecimal currencyValue) {
        this.currencyValue = currencyValue;
        return this;
    }

    public void setCurrencyValue(BigDecimal currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getIp() {
        return ip;
    }

    public OrderLog ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPaymentFlag() {
        return paymentFlag;
    }

    public OrderLog paymentFlag(Integer paymentFlag) {
        this.paymentFlag = paymentFlag;
        return this;
    }

    public void setPaymentFlag(Integer paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getOrderName() {
        return orderName;
    }

    public OrderLog orderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public OrderLog isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public OrderLog createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public OrderLog modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public OrderLog createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public OrderLog modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public OrderLog customerId(Customer customer) {
        this.customerId = customer;
        return this;
    }

    public void setCustomerId(Customer customer) {
        this.customerId = customer;
    }

    public Currency getCurrencyId() {
        return currencyId;
    }

    public OrderLog currencyId(Currency currency) {
        this.currencyId = currency;
        return this;
    }

    public void setCurrencyId(Currency currency) {
        this.currencyId = currency;
    }

    public OrderStatus getOrderStatusId() {
        return orderStatusId;
    }

    public OrderLog orderStatusId(OrderStatus orderStatus) {
        this.orderStatusId = orderStatus;
        return this;
    }

    public void setOrderStatusId(OrderStatus orderStatus) {
        this.orderStatusId = orderStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderLog)) {
            return false;
        }
        return id != null && id.equals(((OrderLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderLog{" +
            "id=" + getId() +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", invocePerfix='" + getInvocePerfix() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", email='" + getEmail() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", shippingFristname='" + getShippingFristname() + "'" +
            ", shippingLastname='" + getShippingLastname() + "'" +
            ", shippingCompany='" + getShippingCompany() + "'" +
            ", shippingAddress1='" + getShippingAddress1() + "'" +
            ", shippingAddress2='" + getShippingAddress2() + "'" +
            ", shippingCity='" + getShippingCity() + "'" +
            ", shippingPostcode='" + getShippingPostcode() + "'" +
            ", shippingCountry='" + getShippingCountry() + "'" +
            ", shippingZone='" + getShippingZone() + "'" +
            ", shippingAddressFormat='" + getShippingAddressFormat() + "'" +
            ", shippingMethod='" + getShippingMethod() + "'" +
            ", paymentFirstname='" + getPaymentFirstname() + "'" +
            ", paymentLastname='" + getPaymentLastname() + "'" +
            ", paymentCompany='" + getPaymentCompany() + "'" +
            ", paymentAddress1='" + getPaymentAddress1() + "'" +
            ", paymentAddress2='" + getPaymentAddress2() + "'" +
            ", paymentCity='" + getPaymentCity() + "'" +
            ", paymentPostcode='" + getPaymentPostcode() + "'" +
            ", paymentCountry='" + getPaymentCountry() + "'" +
            ", paymentZone='" + getPaymentZone() + "'" +
            ", paymentAddressFormat='" + getPaymentAddressFormat() + "'" +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", comment='" + getComment() + "'" +
            ", total=" + getTotal() +
            ", reward=" + getReward() +
            ", commision=" + getCommision() +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", currencyValue=" + getCurrencyValue() +
            ", ip='" + getIp() + "'" +
            ", paymentFlag=" + getPaymentFlag() +
            ", orderName='" + getOrderName() + "'" +
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
