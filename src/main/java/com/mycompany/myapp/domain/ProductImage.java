package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProductImage.
 */
@Entity
@Table(name = "product_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image")
    private String image;

    @Column(name = "container_name")
    private String containerName;

    @Column(name = "defualt_image")
    private Integer defualtImage;

    @Column(name = "sort_order")
    private Integer sortOrder;

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

    @ManyToOne
    @JsonIgnoreProperties("productImages")
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties("productImages")
    private Product productId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public ProductImage image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContainerName() {
        return containerName;
    }

    public ProductImage containerName(String containerName) {
        this.containerName = containerName;
        return this;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public Integer getDefualtImage() {
        return defualtImage;
    }

    public ProductImage defualtImage(Integer defualtImage) {
        this.defualtImage = defualtImage;
        return this;
    }

    public void setDefualtImage(Integer defualtImage) {
        this.defualtImage = defualtImage;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public ProductImage sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public ProductImage isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public ProductImage createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public ProductImage modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public ProductImage createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public ProductImage modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Product getProduct() {
        return product;
    }

    public ProductImage product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProductId() {
        return productId;
    }

    public ProductImage productId(Product product) {
        this.productId = product;
        return this;
    }

    public void setProductId(Product product) {
        this.productId = product;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductImage)) {
            return false;
        }
        return id != null && id.equals(((ProductImage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", containerName='" + getContainerName() + "'" +
            ", defualtImage=" + getDefualtImage() +
            ", sortOrder=" + getSortOrder() +
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
