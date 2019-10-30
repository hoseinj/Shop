package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ShopUser.
 */
@Entity
@Table(name = "shop_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShopUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "code")
    private String code;

    @Column(name = "ip")
    private String ip;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "name")
    private String name;

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

    @OneToMany(mappedBy = "shopUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AccessToken> accessTokens = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("shopUsers")
    private UserGroup userGroupId;

    @ManyToOne
    @JsonIgnoreProperties("shopUsers")
    private UserGroup userGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public ShopUser username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public ShopUser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public ShopUser firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ShopUser lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public ShopUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public ShopUser avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public ShopUser avatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        return this;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getCode() {
        return code;
    }

    public ShopUser code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIp() {
        return ip;
    }

    public ShopUser ip(String ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public ShopUser address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public ShopUser phoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public ShopUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public ShopUser isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public ShopUser createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public ShopUser modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public ShopUser createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public ShopUser modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<AccessToken> getAccessTokens() {
        return accessTokens;
    }

    public ShopUser accessTokens(Set<AccessToken> accessTokens) {
        this.accessTokens = accessTokens;
        return this;
    }

    public ShopUser addAccessToken(AccessToken accessToken) {
        this.accessTokens.add(accessToken);
        accessToken.setShopUser(this);
        return this;
    }

    public ShopUser removeAccessToken(AccessToken accessToken) {
        this.accessTokens.remove(accessToken);
        accessToken.setShopUser(null);
        return this;
    }

    public void setAccessTokens(Set<AccessToken> accessTokens) {
        this.accessTokens = accessTokens;
    }

    public UserGroup getUserGroupId() {
        return userGroupId;
    }

    public ShopUser userGroupId(UserGroup userGroup) {
        this.userGroupId = userGroup;
        return this;
    }

    public void setUserGroupId(UserGroup userGroup) {
        this.userGroupId = userGroup;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public ShopUser userGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
        return this;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShopUser)) {
            return false;
        }
        return id != null && id.equals(((ShopUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ShopUser{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", avatarPath='" + getAvatarPath() + "'" +
            ", code='" + getCode() + "'" +
            ", ip='" + getIp() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", name='" + getName() + "'" +
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
