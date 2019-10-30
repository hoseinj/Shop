package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Order} entity.
 */
public class OrderDTO implements Serializable {

    private Long id;

    private String invoiceNo;

    private String invoicePrefix;

    private String firstname;

    private String lastname;

    private String email;

    private Integer telephone;

    private String fax;

    private String shippingFirstname;

    private String shippingLastname;

    private String shippingCompany;

    private String shippingAddress1;

    private String shippingAddress2;

    private String shippingCity;

    private String shippingPostcode;

    private String shippingCountry;

    private String shippingZone;

    private String shippingAddressFormat;

    private String shippingMethod;

    private String paymentFirstname;

    private String paymentLastname;

    private String paymentCompany;

    private String paymentAddress1;

    private String paymentAddress2;

    private String paymentCity;

    private String paymentPostcode;

    private String paymentCountry;

    private String paymentZone;

    private String paymentAddressFormat;

    private String paymentMethod;

    private String comment;

    private Integer total;

    private Integer reward;

    private Integer commision;

    private String currencyCode;

    private Integer currencyValue;

    private String ip;

    private Integer paymentFlag;

    private String orderName;


    private Long customerIdId;

    private Long currencyIdId;

    private Long orderStatusIdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoicePrefix() {
        return invoicePrefix;
    }

    public void setInvoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getShippingFirstname() {
        return shippingFirstname;
    }

    public void setShippingFirstname(String shippingFirstname) {
        this.shippingFirstname = shippingFirstname;
    }

    public String getShippingLastname() {
        return shippingLastname;
    }

    public void setShippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingZone() {
        return shippingZone;
    }

    public void setShippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
    }

    public String getShippingAddressFormat() {
        return shippingAddressFormat;
    }

    public void setShippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentFirstname() {
        return paymentFirstname;
    }

    public void setPaymentFirstname(String paymentFirstname) {
        this.paymentFirstname = paymentFirstname;
    }

    public String getPaymentLastname() {
        return paymentLastname;
    }

    public void setPaymentLastname(String paymentLastname) {
        this.paymentLastname = paymentLastname;
    }

    public String getPaymentCompany() {
        return paymentCompany;
    }

    public void setPaymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    public String getPaymentAddress1() {
        return paymentAddress1;
    }

    public void setPaymentAddress1(String paymentAddress1) {
        this.paymentAddress1 = paymentAddress1;
    }

    public String getPaymentAddress2() {
        return paymentAddress2;
    }

    public void setPaymentAddress2(String paymentAddress2) {
        this.paymentAddress2 = paymentAddress2;
    }

    public String getPaymentCity() {
        return paymentCity;
    }

    public void setPaymentCity(String paymentCity) {
        this.paymentCity = paymentCity;
    }

    public String getPaymentPostcode() {
        return paymentPostcode;
    }

    public void setPaymentPostcode(String paymentPostcode) {
        this.paymentPostcode = paymentPostcode;
    }

    public String getPaymentCountry() {
        return paymentCountry;
    }

    public void setPaymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
    }

    public String getPaymentZone() {
        return paymentZone;
    }

    public void setPaymentZone(String paymentZone) {
        this.paymentZone = paymentZone;
    }

    public String getPaymentAddressFormat() {
        return paymentAddressFormat;
    }

    public void setPaymentAddressFormat(String paymentAddressFormat) {
        this.paymentAddressFormat = paymentAddressFormat;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getCommision() {
        return commision;
    }

    public void setCommision(Integer commision) {
        this.commision = commision;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Integer currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPaymentFlag() {
        return paymentFlag;
    }

    public void setPaymentFlag(Integer paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Long getCustomerIdId() {
        return customerIdId;
    }

    public void setCustomerIdId(Long customerId) {
        this.customerIdId = customerId;
    }

    public Long getCurrencyIdId() {
        return currencyIdId;
    }

    public void setCurrencyIdId(Long currencyId) {
        this.currencyIdId = currencyId;
    }

    public Long getOrderStatusIdId() {
        return orderStatusIdId;
    }

    public void setOrderStatusIdId(Long orderStatusId) {
        this.orderStatusIdId = orderStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderDTO orderDTO = (OrderDTO) o;
        if (orderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
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
            ", customerId=" + getCustomerIdId() +
            ", currencyId=" + getCurrencyIdId() +
            ", orderStatusId=" + getOrderStatusIdId() +
            "}";
    }
}
