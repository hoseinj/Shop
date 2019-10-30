package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Migrations.
 */
@Entity
@Table(name = "migrations")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Migrations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp", precision = 21, scale = 2)
    private BigDecimal timestamp;

    @Column(name = "name")
    private String name;

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
    private String telephone;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTimestamp() {
        return timestamp;
    }

    public Migrations timestamp(BigDecimal timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(BigDecimal timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public Migrations name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public Migrations invoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoicePrefix() {
        return invoicePrefix;
    }

    public Migrations invoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
        return this;
    }

    public void setInvoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
    }

    public String getFirstname() {
        return firstname;
    }

    public Migrations firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Migrations lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public Migrations email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public Migrations telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public Migrations fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getShippingFirstname() {
        return shippingFirstname;
    }

    public Migrations shippingFirstname(String shippingFirstname) {
        this.shippingFirstname = shippingFirstname;
        return this;
    }

    public void setShippingFirstname(String shippingFirstname) {
        this.shippingFirstname = shippingFirstname;
    }

    public String getShippingLastname() {
        return shippingLastname;
    }

    public Migrations shippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
        return this;
    }

    public void setShippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public Migrations shippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
        return this;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public Migrations shippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
        return this;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public Migrations shippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
        return this;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public Migrations shippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
        return this;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public Migrations shippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
        return this;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public Migrations shippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
        return this;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingZone() {
        return shippingZone;
    }

    public Migrations shippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
        return this;
    }

    public void setShippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
    }

    public String getShippingAddressFormat() {
        return shippingAddressFormat;
    }

    public Migrations shippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
        return this;
    }

    public void setShippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public Migrations shippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentFirstname() {
        return paymentFirstname;
    }

    public Migrations paymentFirstname(String paymentFirstname) {
        this.paymentFirstname = paymentFirstname;
        return this;
    }

    public void setPaymentFirstname(String paymentFirstname) {
        this.paymentFirstname = paymentFirstname;
    }

    public String getPaymentLastname() {
        return paymentLastname;
    }

    public Migrations paymentLastname(String paymentLastname) {
        this.paymentLastname = paymentLastname;
        return this;
    }

    public void setPaymentLastname(String paymentLastname) {
        this.paymentLastname = paymentLastname;
    }

    public String getPaymentCompany() {
        return paymentCompany;
    }

    public Migrations paymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
        return this;
    }

    public void setPaymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    public String getPaymentAddress1() {
        return paymentAddress1;
    }

    public Migrations paymentAddress1(String paymentAddress1) {
        this.paymentAddress1 = paymentAddress1;
        return this;
    }

    public void setPaymentAddress1(String paymentAddress1) {
        this.paymentAddress1 = paymentAddress1;
    }

    public String getPaymentAddress2() {
        return paymentAddress2;
    }

    public Migrations paymentAddress2(String paymentAddress2) {
        this.paymentAddress2 = paymentAddress2;
        return this;
    }

    public void setPaymentAddress2(String paymentAddress2) {
        this.paymentAddress2 = paymentAddress2;
    }

    public String getPaymentCity() {
        return paymentCity;
    }

    public Migrations paymentCity(String paymentCity) {
        this.paymentCity = paymentCity;
        return this;
    }

    public void setPaymentCity(String paymentCity) {
        this.paymentCity = paymentCity;
    }

    public String getPaymentPostcode() {
        return paymentPostcode;
    }

    public Migrations paymentPostcode(String paymentPostcode) {
        this.paymentPostcode = paymentPostcode;
        return this;
    }

    public void setPaymentPostcode(String paymentPostcode) {
        this.paymentPostcode = paymentPostcode;
    }

    public String getPaymentCountry() {
        return paymentCountry;
    }

    public Migrations paymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
        return this;
    }

    public void setPaymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
    }

    public String getPaymentZone() {
        return paymentZone;
    }

    public Migrations paymentZone(String paymentZone) {
        this.paymentZone = paymentZone;
        return this;
    }

    public void setPaymentZone(String paymentZone) {
        this.paymentZone = paymentZone;
    }

    public String getPaymentAddressFormat() {
        return paymentAddressFormat;
    }

    public Migrations paymentAddressFormat(String paymentAddressFormat) {
        this.paymentAddressFormat = paymentAddressFormat;
        return this;
    }

    public void setPaymentAddressFormat(String paymentAddressFormat) {
        this.paymentAddressFormat = paymentAddressFormat;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Migrations paymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public Migrations comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Migrations total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getReward() {
        return reward;
    }

    public Migrations reward(Integer reward) {
        this.reward = reward;
        return this;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public BigDecimal getCommision() {
        return commision;
    }

    public Migrations commision(BigDecimal commision) {
        this.commision = commision;
        return this;
    }

    public void setCommision(BigDecimal commision) {
        this.commision = commision;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Migrations currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getCurrencyValue() {
        return currencyValue;
    }

    public Migrations currencyValue(BigDecimal currencyValue) {
        this.currencyValue = currencyValue;
        return this;
    }

    public void setCurrencyValue(BigDecimal currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getIp() {
        return ip;
    }

    public Migrations ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPaymentFlag() {
        return paymentFlag;
    }

    public Migrations paymentFlag(Integer paymentFlag) {
        this.paymentFlag = paymentFlag;
        return this;
    }

    public void setPaymentFlag(Integer paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getOrderName() {
        return orderName;
    }

    public Migrations orderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public Migrations isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Migrations createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public Migrations modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Migrations createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public Migrations modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Migrations)) {
            return false;
        }
        return id != null && id.equals(((Migrations) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Migrations{" +
            "id=" + getId() +
            ", timestamp=" + getTimestamp() +
            ", name='" + getName() + "'" +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", invoicePrefix='" + getInvoicePrefix() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", email='" + getEmail() + "'" +
            ", telephone='" + getTelephone() + "'" +
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
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
