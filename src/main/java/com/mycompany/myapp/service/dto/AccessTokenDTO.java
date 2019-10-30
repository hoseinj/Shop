package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.AccessToken} entity.
 */
public class AccessTokenDTO implements Serializable {

    private Long id;

    private String token;


    private Long shopUserId;

    private Long shopUserIdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getShopUserId() {
        return shopUserId;
    }

    public void setShopUserId(Long shopUserId) {
        this.shopUserId = shopUserId;
    }

    public Long getShopUserIdId() {
        return shopUserIdId;
    }

    public void setShopUserIdId(Long shopUserId) {
        this.shopUserIdId = shopUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccessTokenDTO accessTokenDTO = (AccessTokenDTO) o;
        if (accessTokenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accessTokenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
            "id=" + getId() +
            ", token='" + getToken() + "'" +
            ", shopUser=" + getShopUserId() +
            ", shopUserId=" + getShopUserIdId() +
            "}";
    }
}
