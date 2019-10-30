import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ProductRatingComponent } from './product-rating.component';
import { ProductRatingDetailComponent } from './product-rating-detail.component';
import { ProductRatingUpdateComponent } from './product-rating-update.component';
import { ProductRatingDeletePopupComponent, ProductRatingDeleteDialogComponent } from './product-rating-delete-dialog.component';
import { productRatingRoute, productRatingPopupRoute } from './product-rating.route';

const ENTITY_STATES = [...productRatingRoute, ...productRatingPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProductRatingComponent,
    ProductRatingDetailComponent,
    ProductRatingUpdateComponent,
    ProductRatingDeleteDialogComponent,
    ProductRatingDeletePopupComponent
  ],
  entryComponents: [ProductRatingDeleteDialogComponent]
})
export class ShopProductRatingModule {}
