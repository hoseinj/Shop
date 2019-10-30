package com.mycompany.myapp.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.Address.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Banner.class.getName());
            createCache(cm, com.mycompany.myapp.domain.BannerGroup.class.getName());
            createCache(cm, com.mycompany.myapp.domain.BannerImageDescription.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Category.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CategoryDescription.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CategoryPath.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Contact.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Country.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Currency.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Customer.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Customer.class.getName() + ".productRatings");
            createCache(cm, com.mycompany.myapp.domain.CustomerGroup.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CustomerIp.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CustomerTransaction.class.getName());
            createCache(cm, com.mycompany.myapp.domain.CustomerWishlist.class.getName());
            createCache(cm, com.mycompany.myapp.domain.EmailTemplate.class.getName());
            createCache(cm, com.mycompany.myapp.domain.GeoZone.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Language.class.getName());
            createCache(cm, com.mycompany.myapp.domain.LoginLog.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Manufacturer.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Migrations.class.getName());
            createCache(cm, com.mycompany.myapp.domain.OrderHistory.class.getName());
            createCache(cm, com.mycompany.myapp.domain.OrderLog.class.getName());
            createCache(cm, com.mycompany.myapp.domain.OrderOption.class.getName());
            createCache(cm, com.mycompany.myapp.domain.OrderProduct.class.getName());
            createCache(cm, com.mycompany.myapp.domain.OrderStatus.class.getName());
            createCache(cm, com.mycompany.myapp.domain.OrderTotal.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ShopPage.class.getName());
            createCache(cm, com.mycompany.myapp.domain.PageGroup.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Product.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Product.class.getName() + ".categories");
            createCache(cm, com.mycompany.myapp.domain.Product.class.getName() + ".productImages");
            createCache(cm, com.mycompany.myapp.domain.Product.class.getName() + ".wishLists");
            createCache(cm, com.mycompany.myapp.domain.Product.class.getName() + ".productRelatedS");
            createCache(cm, com.mycompany.myapp.domain.Product.class.getName() + ".productRatings");
            createCache(cm, com.mycompany.myapp.domain.Product.class.getName() + ".orderProducts");
            createCache(cm, com.mycompany.myapp.domain.PorductDescription.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ProductDiscount.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ProductImage.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ProductRating.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ProductRelated.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ProductTag.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ProductViewLog.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Setting.class.getName());
            createCache(cm, com.mycompany.myapp.domain.StockStatus.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ShopUser.class.getName());
            createCache(cm, com.mycompany.myapp.domain.ShopUser.class.getName() + ".accessTokens");
            createCache(cm, com.mycompany.myapp.domain.UserGroup.class.getName());
            createCache(cm, com.mycompany.myapp.domain.UserGroup.class.getName() + ".shopUsers");
            createCache(cm, com.mycompany.myapp.domain.Zone.class.getName());
            createCache(cm, com.mycompany.myapp.domain.AccessToken.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Order.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Order.class.getName() + ".porductLists");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
