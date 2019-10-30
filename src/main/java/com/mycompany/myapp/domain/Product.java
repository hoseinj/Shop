package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "upc")
    private String upc;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "image")
    private String image;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "shipping")
    private Integer shipping;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Column(name = "date_available")
    private String dateAvailable;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "meta_tag_title")
    private String metaTagTitle;

    @Column(name = "meta_tag_description")
    private String metaTagDescription;

    @Column(name = "meta_tag_keyword")
    private String metaTagKeyword;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "subtract_stock")
    private Boolean subtractStock;

    @Column(name = "minimum_quantity")
    private Integer minimumQuantity;

    @Column(name = "location")
    private String location;

    @Column(name = "wishlist_status")
    private Integer wishlistStatus;

    @Column(name = "delet_flag")
    private String deletFlag;

    @Column(name = "is_featured")
    private Integer isFeatured;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "jhi_condition")
    private Integer condition;

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

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductImage> productImages = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CustomerWishlist> wishLists = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductRelated> productRelatedS = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductRating> productRatings = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrderProduct> orderProducts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("products")
    private StockStatus stockStatusid;

    @ManyToOne
    @JsonIgnoreProperties("products")
    private Manufacturer manufacturerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public Product sku(String sku) {
        this.sku = sku;
        return this;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUpc() {
        return upc;
    }

    public Product upc(String upc) {
        this.upc = upc;
        return this;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public Product image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Product imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getShipping() {
        return shipping;
    }

    public Product shipping(Integer shipping) {
        this.shipping = shipping;
        return this;
    }

    public void setShipping(Integer shipping) {
        this.shipping = shipping;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDateAvailable() {
        return dateAvailable;
    }

    public Product dateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
        return this;
    }

    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Product sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Product description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public Product amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public Product metaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
        return this;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public String getMetaTagDescription() {
        return metaTagDescription;
    }

    public Product metaTagDescription(String metaTagDescription) {
        this.metaTagDescription = metaTagDescription;
        return this;
    }

    public void setMetaTagDescription(String metaTagDescription) {
        this.metaTagDescription = metaTagDescription;
    }

    public String getMetaTagKeyword() {
        return metaTagKeyword;
    }

    public Product metaTagKeyword(String metaTagKeyword) {
        this.metaTagKeyword = metaTagKeyword;
        return this;
    }

    public void setMetaTagKeyword(String metaTagKeyword) {
        this.metaTagKeyword = metaTagKeyword;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Product discount(Integer discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean isSubtractStock() {
        return subtractStock;
    }

    public Product subtractStock(Boolean subtractStock) {
        this.subtractStock = subtractStock;
        return this;
    }

    public void setSubtractStock(Boolean subtractStock) {
        this.subtractStock = subtractStock;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public Product minimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
        return this;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public String getLocation() {
        return location;
    }

    public Product location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getWishlistStatus() {
        return wishlistStatus;
    }

    public Product wishlistStatus(Integer wishlistStatus) {
        this.wishlistStatus = wishlistStatus;
        return this;
    }

    public void setWishlistStatus(Integer wishlistStatus) {
        this.wishlistStatus = wishlistStatus;
    }

    public String getDeletFlag() {
        return deletFlag;
    }

    public Product deletFlag(String deletFlag) {
        this.deletFlag = deletFlag;
        return this;
    }

    public void setDeletFlag(String deletFlag) {
        this.deletFlag = deletFlag;
    }

    public Integer getIsFeatured() {
        return isFeatured;
    }

    public Product isFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
        return this;
    }

    public void setIsFeatured(Integer isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Integer getRating() {
        return rating;
    }

    public Product rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCondition() {
        return condition;
    }

    public Product condition(Integer condition) {
        this.condition = condition;
        return this;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public Product isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Product createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public Product modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Product createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public Product modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Product categories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public Product addCategories(Category category) {
        this.categories.add(category);
        category.setProduct(this);
        return this;
    }

    public Product removeCategories(Category category) {
        this.categories.remove(category);
        category.setProduct(null);
        return this;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<ProductImage> getProductImages() {
        return productImages;
    }

    public Product productImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
        return this;
    }

    public Product addProductImages(ProductImage productImage) {
        this.productImages.add(productImage);
        productImage.setProduct(this);
        return this;
    }

    public Product removeProductImages(ProductImage productImage) {
        this.productImages.remove(productImage);
        productImage.setProduct(null);
        return this;
    }

    public void setProductImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public Set<CustomerWishlist> getWishLists() {
        return wishLists;
    }

    public Product wishLists(Set<CustomerWishlist> customerWishlists) {
        this.wishLists = customerWishlists;
        return this;
    }

    public Product addWishLists(CustomerWishlist customerWishlist) {
        this.wishLists.add(customerWishlist);
        customerWishlist.setProduct(this);
        return this;
    }

    public Product removeWishLists(CustomerWishlist customerWishlist) {
        this.wishLists.remove(customerWishlist);
        customerWishlist.setProduct(null);
        return this;
    }

    public void setWishLists(Set<CustomerWishlist> customerWishlists) {
        this.wishLists = customerWishlists;
    }

    public Set<ProductRelated> getProductRelatedS() {
        return productRelatedS;
    }

    public Product productRelatedS(Set<ProductRelated> productRelateds) {
        this.productRelatedS = productRelateds;
        return this;
    }

    public Product addProductRelatedS(ProductRelated productRelated) {
        this.productRelatedS.add(productRelated);
        productRelated.setProduct(this);
        return this;
    }

    public Product removeProductRelatedS(ProductRelated productRelated) {
        this.productRelatedS.remove(productRelated);
        productRelated.setProduct(null);
        return this;
    }

    public void setProductRelatedS(Set<ProductRelated> productRelateds) {
        this.productRelatedS = productRelateds;
    }

    public Set<ProductRating> getProductRatings() {
        return productRatings;
    }

    public Product productRatings(Set<ProductRating> productRatings) {
        this.productRatings = productRatings;
        return this;
    }

    public Product addProductRatings(ProductRating productRating) {
        this.productRatings.add(productRating);
        productRating.setProduct(this);
        return this;
    }

    public Product removeProductRatings(ProductRating productRating) {
        this.productRatings.remove(productRating);
        productRating.setProduct(null);
        return this;
    }

    public void setProductRatings(Set<ProductRating> productRatings) {
        this.productRatings = productRatings;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public Product orderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
        return this;
    }

    public Product addOrderProducts(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
        orderProduct.setProduct(this);
        return this;
    }

    public Product removeOrderProducts(OrderProduct orderProduct) {
        this.orderProducts.remove(orderProduct);
        orderProduct.setProduct(null);
        return this;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public StockStatus getStockStatusid() {
        return stockStatusid;
    }

    public Product stockStatusid(StockStatus stockStatus) {
        this.stockStatusid = stockStatus;
        return this;
    }

    public void setStockStatusid(StockStatus stockStatus) {
        this.stockStatusid = stockStatus;
    }

    public Manufacturer getManufacturerId() {
        return manufacturerId;
    }

    public Product manufacturerId(Manufacturer manufacturer) {
        this.manufacturerId = manufacturer;
        return this;
    }

    public void setManufacturerId(Manufacturer manufacturer) {
        this.manufacturerId = manufacturer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Product{" +
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
            "}";
    }
}
