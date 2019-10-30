package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "mail_status")
    private Integer mailStatus;

    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "lastlogin")
    private String lastlogin;

    @Column(name = "newsletter")
    private Long newsletter;

    @Column(name = "safe")
    private Long safe;

    @Column(name = "ip")
    private String ip;

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

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductRating> productRatings = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("customers")
    private Country countryId;

    @ManyToOne
    @JsonIgnoreProperties("customers")
    private Zone zoneId;

    @ManyToOne
    @JsonIgnoreProperties("customers")
    private CustomerGroup customerGroupId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public Customer username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Customer password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public Customer mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public Customer address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public Customer city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public Customer pincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAvatar() {
        return avatar;
    }

    public Customer avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public Customer avatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        return this;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Integer getMailStatus() {
        return mailStatus;
    }

    public Customer mailStatus(Integer mailStatus) {
        this.mailStatus = mailStatus;
        return this;
    }

    public void setMailStatus(Integer mailStatus) {
        this.mailStatus = mailStatus;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public Customer deleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
        return this;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public Customer lastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
        return this;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Long getNewsletter() {
        return newsletter;
    }

    public Customer newsletter(Long newsletter) {
        this.newsletter = newsletter;
        return this;
    }

    public void setNewsletter(Long newsletter) {
        this.newsletter = newsletter;
    }

    public Long getSafe() {
        return safe;
    }

    public Customer safe(Long safe) {
        this.safe = safe;
        return this;
    }

    public void setSafe(Long safe) {
        this.safe = safe;
    }

    public String getIp() {
        return ip;
    }

    public Customer ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public Customer isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Customer createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public Customer modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Customer createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public Customer modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<ProductRating> getProductRatings() {
        return productRatings;
    }

    public Customer productRatings(Set<ProductRating> productRatings) {
        this.productRatings = productRatings;
        return this;
    }

    public Customer addProductRating(ProductRating productRating) {
        this.productRatings.add(productRating);
        productRating.setCustomer(this);
        return this;
    }

    public Customer removeProductRating(ProductRating productRating) {
        this.productRatings.remove(productRating);
        productRating.setCustomer(null);
        return this;
    }

    public void setProductRatings(Set<ProductRating> productRatings) {
        this.productRatings = productRatings;
    }

    public Country getCountryId() {
        return countryId;
    }

    public Customer countryId(Country country) {
        this.countryId = country;
        return this;
    }

    public void setCountryId(Country country) {
        this.countryId = country;
    }

    public Zone getZoneId() {
        return zoneId;
    }

    public Customer zoneId(Zone zone) {
        this.zoneId = zone;
        return this;
    }

    public void setZoneId(Zone zone) {
        this.zoneId = zone;
    }

    public CustomerGroup getCustomerGroupId() {
        return customerGroupId;
    }

    public Customer customerGroupId(CustomerGroup customerGroup) {
        this.customerGroupId = customerGroup;
        return this;
    }

    public void setCustomerGroupId(CustomerGroup customerGroup) {
        this.customerGroupId = customerGroup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", pincode='" + getPincode() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", avatarPath='" + getAvatarPath() + "'" +
            ", mailStatus=" + getMailStatus() +
            ", deleteFlag=" + getDeleteFlag() +
            ", lastlogin='" + getLastlogin() + "'" +
            ", newsletter=" + getNewsletter() +
            ", safe=" + getSafe() +
            ", ip='" + getIp() + "'" +
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
