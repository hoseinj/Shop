package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Product} entity.
 */
public class ProductDTO implements Serializable {

    private Long id;

    private String sku;

    private String upc;

    private Integer quantity;

    private String image;

    private String imagePath;

    private Integer shipping;

    private BigDecimal price;

    private String dateAvailable;

    private Integer sortOrder;

    private String name;

    private String description;

    private Float amount;

    private String metaTagTitle;

    private String metaTagDescription;

    private String metaTagKeyword;

    private Integer discount;

    private Boolean subtractStock;

    private Integer minimumQuantity;

    private String location;

    private Integer wishlistStatus;

    private String deletFlag;

    private Integer isFeatured;

    private Integer rating;

    private Integer condition;

    private Integer isActive;

    private Long createdBy;

    private Long modifiedBy;

    private String createdDate;

    private String modifiedDate;


    private Long stockStatusidId;

    private Long manufacturerIdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getShipping() {
        return shipping;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public String getMetaTagDescription() {
        return metaTagDescription;
    }

    public void setMetaTagDescription(String metaTagDescription) {
        this.metaTagDescription = metaTagDescription;
    }

    public String getMetaTagKeyword() {
        return metaTagKeyword;
    }

    public void setMetaTagKeyword(String metaTagKeyword) {
        this.metaTagKeyword = metaTagKeyword;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean isSubtractStock() {
        return subtractStock;
    }

    public void setSubtractStock(Boolean subtractStock) {
        this.subtractStock = subtractStock;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getWishlistStatus() {
        return wishlistStatus;
    }

    public void setWishlistStatus(Integer wishlistStatus) {
        this.wishlistStatus = wishlistStatus;
    }

    public String getDeletFlag() {
        return deletFlag;
    }

    public void setDeletFlag(String deletFlag) {
        this.deletFlag = deletFlag;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
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

    public Long getStockStatusidId() {
        return stockStatusidId;
    }

    public void setStockStatusidId(Long stockStatusId) {
        this.stockStatusidId = stockStatusId;
    }

    public Long getManufacturerIdId() {
        return manufacturerIdId;
    }

    public void setManufacturerIdId(Long manufacturerId) {
        this.manufacturerIdId = manufacturerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (productDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", sku='" + getSku() + "'" +
            ", upc='" + getUpc() + "'" +
            ", quantity=" + getQuantity() +
            ", image='" + getImage() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", shipping=" + getShipping() +
            ", price=" + getPrice() +
            ", dateAvailable='" + getDateAvailable() + "'" +
            ", sortOrder=" + getSortOrder() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", amount=" + getAmount() +
            ", metaTagTitle='" + getMetaTagTitle() + "'" +
            ", metaTagDescription='" + getMetaTagDescription() + "'" +
            ", metaTagKeyword='" + getMetaTagKeyword() + "'" +
            ", discount=" + getDiscount() +
            ", subtractStock='" + isSubtractStock() + "'" +
            ", minimumQuantity=" + getMinimumQuantity() +
            ", location='" + getLocation() + "'" +
            ", wishlistStatus=" + getWishlistStatus() +
            ", deletFlag='" + getDeletFlag() + "'" +
            ", isFeatured=" + getIsFeatured() +
            ", rating=" + getRating() +
            ", condition=" + getCondition() +
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", stockStatusid=" + getStockStatusidId() +
            ", manufacturerId=" + getManufacturerIdId() +
            "}";
    }
}
