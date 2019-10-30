package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Setting.
 */
@Entity
@Table(name = "setting")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "meta_tag_title")
    private String metaTagTitle;

    @Column(name = "meta_tag_description")
    private String metaTagDescription;

    @Column(name = "meta_tag_keywords")
    private String metaTagKeywords;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_owner")
    private String storeOwner;

    @Column(name = "store_address")
    private String storeAddress;

    @Column(name = "store_email")
    private String storeEmail;

    @Column(name = "store_telephone")
    private String storeTelephone;

    @Column(name = "store_fax")
    private String storeFax;

    @Column(name = "store_logo")
    private String storeLogo;

    @Column(name = "store_logo_path")
    private String storeLogoPath;

    @Column(name = "maintenance_mode")
    private Integer maintenanceMode;

    @Column(name = "store_language_name")
    private String storeLanguageName;

    @Column(name = "store_image")
    private String storeImage;

    @Column(name = "store_image_path")
    private String storeImagePath;

    @Column(name = "google")
    private String google;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "invoice_prefix")
    private String invoicePrefix;

    @Column(name = "items_per_page")
    private Integer itemsPerPage;

    @Column(name = "category_product_count")
    private Integer categoryProductCount;

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
    @JsonIgnoreProperties("settings")
    private Country countryId;

    @ManyToOne
    @JsonIgnoreProperties("settings")
    private Zone zoneId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public Setting url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public Setting metaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
        return this;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public String getMetaTagDescription() {
        return metaTagDescription;
    }

    public Setting metaTagDescription(String metaTagDescription) {
        this.metaTagDescription = metaTagDescription;
        return this;
    }

    public void setMetaTagDescription(String metaTagDescription) {
        this.metaTagDescription = metaTagDescription;
    }

    public String getMetaTagKeywords() {
        return metaTagKeywords;
    }

    public Setting metaTagKeywords(String metaTagKeywords) {
        this.metaTagKeywords = metaTagKeywords;
        return this;
    }

    public void setMetaTagKeywords(String metaTagKeywords) {
        this.metaTagKeywords = metaTagKeywords;
    }

    public String getStoreName() {
        return storeName;
    }

    public Setting storeName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreOwner() {
        return storeOwner;
    }

    public Setting storeOwner(String storeOwner) {
        this.storeOwner = storeOwner;
        return this;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public Setting storeAddress(String storeAddress) {
        this.storeAddress = storeAddress;
        return this;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public Setting storeEmail(String storeEmail) {
        this.storeEmail = storeEmail;
        return this;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getStoreTelephone() {
        return storeTelephone;
    }

    public Setting storeTelephone(String storeTelephone) {
        this.storeTelephone = storeTelephone;
        return this;
    }

    public void setStoreTelephone(String storeTelephone) {
        this.storeTelephone = storeTelephone;
    }

    public String getStoreFax() {
        return storeFax;
    }

    public Setting storeFax(String storeFax) {
        this.storeFax = storeFax;
        return this;
    }

    public void setStoreFax(String storeFax) {
        this.storeFax = storeFax;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public Setting storeLogo(String storeLogo) {
        this.storeLogo = storeLogo;
        return this;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getStoreLogoPath() {
        return storeLogoPath;
    }

    public Setting storeLogoPath(String storeLogoPath) {
        this.storeLogoPath = storeLogoPath;
        return this;
    }

    public void setStoreLogoPath(String storeLogoPath) {
        this.storeLogoPath = storeLogoPath;
    }

    public Integer getMaintenanceMode() {
        return maintenanceMode;
    }

    public Setting maintenanceMode(Integer maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
        return this;
    }

    public void setMaintenanceMode(Integer maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public String getStoreLanguageName() {
        return storeLanguageName;
    }

    public Setting storeLanguageName(String storeLanguageName) {
        this.storeLanguageName = storeLanguageName;
        return this;
    }

    public void setStoreLanguageName(String storeLanguageName) {
        this.storeLanguageName = storeLanguageName;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public Setting storeImage(String storeImage) {
        this.storeImage = storeImage;
        return this;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreImagePath() {
        return storeImagePath;
    }

    public Setting storeImagePath(String storeImagePath) {
        this.storeImagePath = storeImagePath;
        return this;
    }

    public void setStoreImagePath(String storeImagePath) {
        this.storeImagePath = storeImagePath;
    }

    public String getGoogle() {
        return google;
    }

    public Setting google(String google) {
        this.google = google;
        return this;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getFacebook() {
        return facebook;
    }

    public Setting facebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public Setting twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public Setting instagram(String instagram) {
        this.instagram = instagram;
        return this;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public Setting orderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getInvoicePrefix() {
        return invoicePrefix;
    }

    public Setting invoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
        return this;
    }

    public void setInvoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public Setting itemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        return this;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Integer getCategoryProductCount() {
        return categoryProductCount;
    }

    public Setting categoryProductCount(Integer categoryProductCount) {
        this.categoryProductCount = categoryProductCount;
        return this;
    }

    public void setCategoryProductCount(Integer categoryProductCount) {
        this.categoryProductCount = categoryProductCount;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public Setting isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Setting createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public Setting modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Setting createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public Setting modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Country getCountryId() {
        return countryId;
    }

    public Setting countryId(Country country) {
        this.countryId = country;
        return this;
    }

    public void setCountryId(Country country) {
        this.countryId = country;
    }

    public Zone getZoneId() {
        return zoneId;
    }

    public Setting zoneId(Zone zone) {
        this.zoneId = zone;
        return this;
    }

    public void setZoneId(Zone zone) {
        this.zoneId = zone;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Setting)) {
            return false;
        }
        return id != null && id.equals(((Setting) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Setting{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", metaTagTitle='" + getMetaTagTitle() + "'" +
            ", metaTagDescription='" + getMetaTagDescription() + "'" +
            ", metaTagKeywords='" + getMetaTagKeywords() + "'" +
            ", storeName='" + getStoreName() + "'" +
            ", storeOwner='" + getStoreOwner() + "'" +
            ", storeAddress='" + getStoreAddress() + "'" +
            ", storeEmail='" + getStoreEmail() + "'" +
            ", storeTelephone='" + getStoreTelephone() + "'" +
            ", storeFax='" + getStoreFax() + "'" +
            ", storeLogo='" + getStoreLogo() + "'" +
            ", storeLogoPath='" + getStoreLogoPath() + "'" +
            ", maintenanceMode=" + getMaintenanceMode() +
            ", storeLanguageName='" + getStoreLanguageName() + "'" +
            ", storeImage='" + getStoreImage() + "'" +
            ", storeImagePath='" + getStoreImagePath() + "'" +
            ", google='" + getGoogle() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", twitter='" + getTwitter() + "'" +
            ", instagram='" + getInstagram() + "'" +
            ", orderStatus=" + getOrderStatus() +
            ", invoicePrefix='" + getInvoicePrefix() + "'" +
            ", itemsPerPage=" + getItemsPerPage() +
            ", categoryProductCount=" + getCategoryProductCount() +
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
