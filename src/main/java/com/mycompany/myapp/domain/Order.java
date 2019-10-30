package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Order.
 */
@Entity
@Table(name = "jhi_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "invoice_prefix")
    private String invoicePrefix;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private Integer telephone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "shipping_firstname")
    private String shippingFirstname;

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

    @Column(name = "total")
    private Integer total;

    @Column(name = "reward")
    private Integer reward;

    @Column(name = "commision")
    private Integer commision;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "currency_value")
    private Integer currencyValue;

    @Column(name = "ip")
    private String ip;

    @Column(name = "payment_flag")
    private Integer paymentFlag;

    @Column(name = "order_name")
    private String orderName;

    @OneToMany(mappedBy = "order")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderProduct> porductLists = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private Customer customerId;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private Currency currencyId;

    @ManyToOne
    @JsonIgnoreProperties("orders")
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

    public Order invoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoicePrefix() {
        return invoicePrefix;
    }

    public Order invoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
        return this;
    }

    public void setInvoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
    }

    public String getFirstname() {
        return firstname;
    }

    public Order firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Order lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public Order email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public Order telephone(Integer telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public Order fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getShippingFirstname() {
        return shippingFirstname;
    }

    public Order shippingFirstname(String shippingFirstname) {
        this.shippingFirstname = shippingFirstname;
        return this;
    }

    public void setShippingFirstname(String shippingFirstname) {
        this.shippingFirstname = shippingFirstname;
    }

    public String getShippingLastname() {
        return shippingLastname;
    }

    public Order shippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
        return this;
    }

    public void setShippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public Order shippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
        return this;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public Order shippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
        return this;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public Order shippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
        return this;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public Order shippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
        return this;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public Order shippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
        return this;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public Order shippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
        return this;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingZone() {
        return shippingZone;
    }

    public Order shippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
        return this;
    }

    public void setShippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
    }

    public String getShippingAddressFormat() {
        return shippingAddressFormat;
    }

    public Order shippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
        return this;
    }

    public void setShippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public Order shippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentFirstname() {
        return paymentFirstname;
    }

    public Order paymentFirstname(String paymentFirstname) {
        this.paymentFirstname = paymentFirstname;
        return this;
    }

    public void setPaymentFirstname(String paymentFirstname) {
        this.paymentFirstname = paymentFirstname;
    }

    public String getPaymentLastname() {
        return paymentLastname;
    }

    public Order paymentLastname(String paymentLastname) {
        this.paymentLastname = paymentLastname;
        return this;
    }

    public void setPaymentLastname(String paymentLastname) {
        this.paymentLastname = paymentLastname;
    }

    public String getPaymentCompany() {
        return paymentCompany;
    }

    public Order paymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
        return this;
    }

    public void setPaymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    public String getPaymentAddress1() {
        return paymentAddress1;
    }

    public Order paymentAddress1(String paymentAddress1) {
        this.paymentAddress1 = paymentAddress1;
        return this;
    }

    public void setPaymentAddress1(String paymentAddress1) {
        this.paymentAddress1 = paymentAddress1;
    }

    public String getPaymentAddress2() {
        return paymentAddress2;
    }

    public Order paymentAddress2(String paymentAddress2) {
        this.paymentAddress2 = paymentAddress2;
        return this;
    }

    public void setPaymentAddress2(String paymentAddress2) {
        this.paymentAddress2 = paymentAddress2;
    }

    public String getPaymentCity() {
        return paymentCity;
    }

    public Order paymentCity(String paymentCity) {
        this.paymentCity = paymentCity;
        return this;
    }

    public void setPaymentCity(String paymentCity) {
        this.paymentCity = paymentCity;
    }

    public String getPaymentPostcode() {
        return paymentPostcode;
    }

    public Order paymentPostcode(String paymentPostcode) {
        this.paymentPostcode = paymentPostcode;
        return this;
    }

    public void setPaymentPostcode(String paymentPostcode) {
        this.paymentPostcode = paymentPostcode;
    }

    public String getPaymentCountry() {
        return paymentCountry;
    }

    public Order paymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
        return this;
    }

    public void setPaymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
    }

    public String getPaymentZone() {
        return paymentZone;
    }

    public Order paymentZone(String paymentZone) {
        this.paymentZone = paymentZone;
        return this;
    }

    public void setPaymentZone(String paymentZone) {
        this.paymentZone = paymentZone;
    }

    public String getPaymentAddressFormat() {
        return paymentAddressFormat;
    }

    public Order paymentAddressFormat(String paymentAddressFormat) {
        this.paymentAddressFormat = paymentAddressFormat;
        return this;
    }

    public void setPaymentAddressFormat(String paymentAddressFormat) {
        this.paymentAddressFormat = paymentAddressFormat;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Order paymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public Order comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTotal() {
        return total;
    }

    public Order total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getReward() {
        return reward;
    }

    public Order reward(Integer reward) {
        this.reward = reward;
        return this;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getCommision() {
        return commision;
    }

    public Order commision(Integer commision) {
        this.commision = commision;
        return this;
    }

    public void setCommision(Integer commision) {
        this.commision = commision;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Order currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getCurrencyValue() {
        return currencyValue;
    }

    public Order currencyValue(Integer currencyValue) {
        this.currencyValue = currencyValue;
        return this;
    }

    public void setCurrencyValue(Integer currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getIp() {
        return ip;
    }

    public Order ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPaymentFlag() {
        return paymentFlag;
    }

    public Order paymentFlag(Integer paymentFlag) {
        this.paymentFlag = paymentFlag;
        return this;
    }

    public void setPaymentFlag(Integer paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getOrderName() {
        return orderName;
    }

    public Order orderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Set<OrderProduct> getPorductLists() {
        return porductLists;
    }

    public Order porductLists(Set<OrderProduct> orderProducts) {
        this.porductLists = orderProducts;
        return this;
    }

    public Order addPorductLists(OrderProduct orderProduct) {
        this.porductLists.add(orderProduct);
        orderProduct.setOrder(this);
        return this;
    }

    public Order removePorductLists(OrderProduct orderProduct) {
        this.porductLists.remove(orderProduct);
        orderProduct.setOrder(null);
        return this;
    }

    public void setPorductLists(Set<OrderProduct> orderProducts) {
        this.porductLists = orderProducts;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public Order customerId(Customer customer) {
        this.customerId = customer;
        return this;
    }

    public void setCustomerId(Customer customer) {
        this.customerId = customer;
    }

    public Currency getCurrencyId() {
        return currencyId;
    }

    public Order currencyId(Currency currency) {
        this.currencyId = currency;
        return this;
    }

    public void setCurrencyId(Currency currency) {
        this.currencyId = currency;
    }

    public OrderStatus getOrderStatusId() {
        return orderStatusId;
    }

    public Order orderStatusId(OrderStatus orderStatus) {
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
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", invoicePrefix='" + getInvoicePrefix() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", email='" + getEmail() + "'" +
            ", telephone=" + getTelephone() +
            ", fax='" + getFax() + "'" +
            ", shippingFirstname='" + getShippingFirstname() + "'" +
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
            "}";
    }
}
