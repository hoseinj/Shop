package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ShopPage.
 */
@Entity
@Table(name = "shop_page")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShopPage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "intro")
    private String intro;

    @Column(name = "full_text")
    private String fullText;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "meta_tag_title")
    private String metaTagTitle;

    @Column(name = "meta_tag_descroption")
    private String metaTagDescroption;

    @Column(name = "meta_tag_keywords")
    private String metaTagKeywords;

    @Column(name = "view_page_count")
    private Integer viewPageCount;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public ShopPage title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public ShopPage intro(String intro) {
        this.intro = intro;
        return this;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFullText() {
        return fullText;
    }

    public ShopPage fullText(String fullText) {
        this.fullText = fullText;
        return this;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public ShopPage sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getMetaTagTitle() {
        return metaTagTitle;
    }

    public ShopPage metaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
        return this;
    }

    public void setMetaTagTitle(String metaTagTitle) {
        this.metaTagTitle = metaTagTitle;
    }

    public String getMetaTagDescroption() {
        return metaTagDescroption;
    }

    public ShopPage metaTagDescroption(String metaTagDescroption) {
        this.metaTagDescroption = metaTagDescroption;
        return this;
    }

    public void setMetaTagDescroption(String metaTagDescroption) {
        this.metaTagDescroption = metaTagDescroption;
    }

    public String getMetaTagKeywords() {
        return metaTagKeywords;
    }

    public ShopPage metaTagKeywords(String metaTagKeywords) {
        this.metaTagKeywords = metaTagKeywords;
        return this;
    }

    public void setMetaTagKeywords(String metaTagKeywords) {
        this.metaTagKeywords = metaTagKeywords;
    }

    public Integer getViewPageCount() {
        return viewPageCount;
    }

    public ShopPage viewPageCount(Integer viewPageCount) {
        this.viewPageCount = viewPageCount;
        return this;
    }

    public void setViewPageCount(Integer viewPageCount) {
        this.viewPageCount = viewPageCount;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public ShopPage isActive(Integer isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public ShopPage createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public ShopPage modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public ShopPage createdDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public ShopPage modifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShopPage)) {
            return false;
        }
        return id != null && id.equals(((ShopPage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ShopPage{" +
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
