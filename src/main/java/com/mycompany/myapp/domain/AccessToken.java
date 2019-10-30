package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A AccessToken.
 */
@Entity
@Table(name = "access_token")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JsonIgnoreProperties("accessTokens")
    private ShopUser shopUser;

    @ManyToOne
    @JsonIgnoreProperties("accessTokens")
    private ShopUser shopUserId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public AccessToken token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ShopUser getShopUser() {
        return shopUser;
    }

    public AccessToken shopUser(ShopUser shopUser) {
        this.shopUser = shopUser;
        return this;
    }

    public void setShopUser(ShopUser shopUser) {
        this.shopUser = shopUser;
    }

    public ShopUser getShopUserId() {
        return shopUserId;
    }

    public AccessToken shopUserId(ShopUser shopUser) {
        this.shopUserId = shopUser;
        return this;
    }

    public void setShopUserId(ShopUser shopUser) {
        this.shopUserId = shopUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccessToken)) {
            return false;
        }
        return id != null && id.equals(((AccessToken) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            "}";
    }
}
