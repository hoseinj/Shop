import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { CustomerWishlistComponent } from './customer-wishlist.component';
import { CustomerWishlistDetailComponent } from './customer-wishlist-detail.component';
import { CustomerWishlistUpdateComponent } from './customer-wishlist-update.component';
import { CustomerWishlistDeletePopupComponent, CustomerWishlistDeleteDialogComponent } from './customer-wishlist-delete-dialog.component';
import { customerWishlistRoute, customerWishlistPopupRoute } from './customer-wishlist.route';

const ENTITY_STATES = [...customerWishlistRoute, ...customerWishlistPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CustomerWishlistComponent,
    CustomerWishlistDetailComponent,
    CustomerWishlistUpdateComponent,
    CustomerWishlistDeleteDialogComponent,
    CustomerWishlistDeletePopupComponent
  ],
  entryComponents: [CustomerWishlistDeleteDialogComponent]
})
export class ShopCustomerWishlistModule {}
