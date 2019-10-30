package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ShopPage} entity.
 */
public class ShopPageDTO implements Serializable {

    private Long id;

    private String title;

    private String intro;

    private String fullText;

    private Integer sortOrder;

    private String metaTagTitle;

    private String metaTagDescroption;

    private String metaTagKeywords;

    private Integer viewPageCount;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public String getMetaTagDescroption() {
        return metaTagDescroption;
    }

    public void setMetaTagDescroption(String metaTagDescroption) {
        this.metaTagDescroption = metaTagDescroption;
    }

    public String getMetaTagKeywords() {
        return metaTagKeywords;
    }

    public void setMetaTagKeywords(String metaTagKeywords) {
        this.metaTagKeywords = metaTagKeywords;
    }

    public Integer getViewPageCount() {
        return viewPageCount;
    }

    public void setViewPageCount(Integer viewPageCount) {
        this.viewPageCount = viewPageCount;
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

        ShopPageDTO shopPageDTO = (ShopPageDTO) o;
        if (shopPageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shopPageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShopPageDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", intro='" + getIntro() + "'" +
            ", fullText='" + getFullText() + "'" +
            ", sortOrder=" + getSortOrder() +
            ", metaTagTitle='" + getMetaTagTitle() + "'" +
            ", metaTagDescroption='" + getMetaTagDescroption() + "'" +
            ", metaTagKeywords='" + getMetaTagKeywords() + "'" +
            ", viewPageCount=" + getViewPageCount() +
            ", isActive=" + getIsActive() +
            ", createdBy=" + getCreatedBy() +
            ", modifiedBy=" + getModifiedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
