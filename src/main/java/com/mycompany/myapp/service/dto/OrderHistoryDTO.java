package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.OrderHistory} entity.
 */
public class OrderHistoryDTO implements Serializable {

    private Long id;

    private String notify;

    private String comment;

    private String dateAdded;

    private Integer isActive;

    private Long createdBy;

    private Long modifiedBy;

    private String createdDate;

    private String modifiedDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderHistoryDTO orderHistoryDTO = (OrderHistoryDTO) o;
        if (orderHistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderHistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderHistoryDTO{" +
            "id=" + getId() +
            ", notify='" + getNotify() + "'" +
            ", comment='" + getComment() + "'" +
            ", dateAdded='" + getDateAdded() + "'" +
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
