package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Customer} entity.
 */
public class CustomerDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private String mobile;

    private String address;

    private String city;

    private String pincode;

    private String avatar;

    private String avatarPath;

    private Integer mailStatus;

    private Integer deleteFlag;

    private String lastlogin;

    private Long newsletter;

    private Long safe;

    private String ip;

    private Integer isActive;

    private Long createdBy;

    private Long modifiedBy;

    private String createdDate;

    private String modifiedDate;


    private Long countryIdId;

    private Long zoneIdId;

    private Long customerGroupIdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Integer getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(Integer mailStatus) {
        this.mailStatus = mailStatus;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Long getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Long newsletter) {
        this.newsletter = newsletter;
    }

    public Long getSafe() {
        return safe;
    }

    public void setSafe(Long safe) {
        this.safe = safe;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getCountryIdId() {
        return countryIdId;
    }

    public void setCountryIdId(Long countryId) {
        this.countryIdId = countryId;
    }

    public Long getZoneIdId() {
        return zoneIdId;
    }

    public void setZoneIdId(Long zoneId) {
        this.zoneIdId = zoneId;
    }

    public Long getCustomerGroupIdId() {
        return customerGroupIdId;
    }

    public void setCustomerGroupIdId(Long customerGroupId) {
        this.customerGroupIdId = customerGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerDTO customerDTO = (CustomerDTO) o;
        if (customerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
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
            ", countryId=" + getCountryIdId() +
            ", zoneId=" + getZoneIdId() +
            ", customerGroupId=" + getCustomerGroupIdId() +
            "}";
    }
}
