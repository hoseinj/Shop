import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'address',
        loadChildren: () => import('./address/address.module').then(m => m.ShopAddressModule)
      },
      {
        path: 'banner',
        loadChildren: () => import('./banner/banner.module').then(m => m.ShopBannerModule)
      },
      {
        path: 'banner-group',
        loadChildren: () => import('./banner-group/banner-group.module').then(m => m.ShopBannerGroupModule)
      },
      {
        path: 'banner-image-description',
        loadChildren: () =>
          import('./banner-image-description/banner-image-description.module').then(m => m.ShopBannerImageDescriptionModule)
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.ShopCategoryModule)
      },
      {
        path: 'category-description',
        loadChildren: () => import('./category-description/category-description.module').then(m => m.ShopCategoryDescriptionModule)
      },
      {
        path: 'category-path',
        loadChildren: () => import('./category-path/category-path.module').then(m => m.ShopCategoryPathModule)
      },
      {
        path: 'contact',
        loadChildren: () => import('./contact/contact.module').then(m => m.ShopContactModule)
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.ShopCountryModule)
      },
      {
        path: 'currency',
        loadChildren: () => import('./currency/currency.module').then(m => m.ShopCurrencyModule)
      },
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.ShopCustomerModule)
      },
      {
        path: 'customer-group',
        loadChildren: () => import('./customer-group/customer-group.module').then(m => m.ShopCustomerGroupModule)
      },
      {
        path: 'customer-ip',
        loadChildren: () => import('./customer-ip/customer-ip.module').then(m => m.ShopCustomerIpModule)
      },
      {
        path: 'customer-transaction',
        loadChildren: () => import('./customer-transaction/customer-transaction.module').then(m => m.ShopCustomerTransactionModule)
      },
      {
        path: 'customer-wishlist',
        loadChildren: () => import('./customer-wishlist/customer-wishlist.module').then(m => m.ShopCustomerWishlistModule)
      },
      {
        path: 'email-template',
        loadChildren: () => import('./email-template/email-template.module').then(m => m.ShopEmailTemplateModule)
      },
      {
        path: 'geo-zone',
        loadChildren: () => import('./geo-zone/geo-zone.module').then(m => m.ShopGeoZoneModule)
      },
      {
        path: 'language',
        loadChildren: () => import('./language/language.module').then(m => m.ShopLanguageModule)
      },
      {
        path: 'login-log',
        loadChildren: () => import('./login-log/login-log.module').then(m => m.ShopLoginLogModule)
      },
      {
        path: 'manufacturer',
        loadChildren: () => import('./manufacturer/manufacturer.module').then(m => m.ShopManufacturerModule)
      },
      {
        path: 'migrations',
        loadChildren: () => import('./migrations/migrations.module').then(m => m.ShopMigrationsModule)
      },
      {
        path: 'order-history',
        loadChildren: () => import('./order-history/order-history.module').then(m => m.ShopOrderHistoryModule)
      },
      {
        path: 'order-log',
        loadChildren: () => import('./order-log/order-log.module').then(m => m.ShopOrderLogModule)
      },
      {
        path: 'order-option',
        loadChildren: () => import('./order-option/order-option.module').then(m => m.ShopOrderOptionModule)
      },
      {
        path: 'order-product',
        loadChildren: () => import('./order-product/order-product.module').then(m => m.ShopOrderProductModule)
      },
      {
        path: 'order-status',
        loadChildren: () => import('./order-status/order-status.module').then(m => m.ShopOrderStatusModule)
      },
      {
        path: 'order-total',
        loadChildren: () => import('./order-total/order-total.module').then(m => m.ShopOrderTotalModule)
      },
      {
        path: 'shop-page',
        loadChildren: () => import('./shop-page/shop-page.module').then(m => m.ShopShopPageModule)
      },
      {
        path: 'page-group',
        loadChildren: () => import('./page-group/page-group.module').then(m => m.ShopPageGroupModule)
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.ShopProductModule)
      },
      {
        path: 'porduct-description',
        loadChildren: () => import('./porduct-description/porduct-description.module').then(m => m.ShopPorductDescriptionModule)
      },
      {
        path: 'product-discount',
        loadChildren: () => import('./product-discount/product-discount.module').then(m => m.ShopProductDiscountModule)
      },
      {
        path: 'product-image',
        loadChildren: () => import('./product-image/product-image.module').then(m => m.ShopProductImageModule)
      },
      {
        path: 'product-rating',
        loadChildren: () => import('./product-rating/product-rating.module').then(m => m.ShopProductRatingModule)
      },
      {
        path: 'product-related',
        loadChildren: () => import('./product-related/product-related.module').then(m => m.ShopProductRelatedModule)
      },
      {
        path: 'product-tag',
        loadChildren: () => import('./product-tag/product-tag.module').then(m => m.ShopProductTagModule)
      },
      {
        path: 'product-view-log',
        loadChildren: () => import('./product-view-log/product-view-log.module').then(m => m.ShopProductViewLogModule)
      },
      {
        path: 'setting',
        loadChildren: () => import('./setting/setting.module').then(m => m.ShopSettingModule)
      },
      {
        path: 'stock-status',
        loadChildren: () => import('./stock-status/stock-status.module').then(m => m.ShopStockStatusModule)
      },
      {
        path: 'shop-user',
        loadChildren: () => import('./shop-user/shop-user.module').then(m => m.ShopShopUserModule)
      },
      {
        path: 'user-group',
        loadChildren: () => import('./user-group/user-group.module').then(m => m.ShopUserGroupModule)
      },
      {
        path: 'zone',
        loadChildren: () => import('./zone/zone.module').then(m => m.ShopZoneModule)
      },
      {
        path: 'access-token',
        loadChildren: () => import('./access-token/access-token.module').then(m => m.ShopAccessTokenModule)
      },
      {
        path: 'order',
        loadChildren: () => import('./order/order.module').then(m => m.ShopOrderModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class ShopEntityModule {}
