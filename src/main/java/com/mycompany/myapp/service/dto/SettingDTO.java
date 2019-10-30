package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Setting} entity.
 */
public class SettingDTO implements Serializable {

    private Long id;

    private String url;

    private String metaTagTitle;

    private String metaTagDescription;

    private String metaTagKeywords;

    private String storeName;

    private String storeOwner;

    private String storeAddress;

    private String storeEmail;

    private String storeTelephone;

    private String storeFax;

    private String storeLogo;

    private String storeLogoPath;

    private Integer maintenanceMode;

    private String storeLanguageName;

    private String storeImage;

    private String storeImagePath;

    private String google;

    private String facebook;

    private String twitter;

    private String instagram;

    private Integer orderStatus;

    private String invoicePrefix;

    private Integer itemsPerPage;

    private Integer categoryProductCount;

    private Integer isActive;

    private Long createdBy;

    private Long modifiedBy;

    private String createdDate;

    private String modifiedDate;


    private Long countryIdId;

    private Long zoneIdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getMetaTagKeywords() {
        return metaTagKeywords;
    }

    public void setMetaTagKeywords(String metaTagKeywords) {
        this.metaTagKeywords = metaTagKeywords;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getStoreTelephone() {
        return storeTelephone;
    }

    public void setStoreTelephone(String storeTelephone) {
        this.storeTelephone = storeTelephone;
    }

    public String getStoreFax() {
        return storeFax;
    }

    public void setStoreFax(String storeFax) {
        this.storeFax = storeFax;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getStoreLogoPath() {
        return storeLogoPath;
    }

    public void setStoreLogoPath(String storeLogoPath) {
        this.storeLogoPath = storeLogoPath;
    }

    public Integer getMaintenanceMode() {
        return maintenanceMode;
    }

    public void setMaintenanceMode(Integer maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public String getStoreLanguageName() {
        return storeLanguageName;
    }

    public void setStoreLanguageName(String storeLanguageName) {
        this.storeLanguageName = storeLanguageName;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreImagePath() {
        return storeImagePath;
    }

    public void setStoreImagePath(String storeImagePath) {
        this.storeImagePath = storeImagePath;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getInvoicePrefix() {
        return invoicePrefix;
    }

    public void setInvoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Integer getCategoryProductCount() {
        return categoryProductCount;
    }

    public void setCategoryProductCount(Integer categoryProductCount) {
        this.categoryProductCount = categoryProductCount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SettingDTO settingDTO = (SettingDTO) o;
        if (settingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), settingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SettingDTO{" +
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
            ", countryId=" + getCountryIdId() +
            ", zoneId=" + getZoneIdId() +
            "}";
    }
}
