import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ProductDiscountComponent } from './product-discount.component';
import { ProductDiscountDetailComponent } from './product-discount-detail.component';
import { ProductDiscountUpdateComponent } from './product-discount-update.component';
import { ProductDiscountDeletePopupComponent, ProductDiscountDeleteDialogComponent } from './product-discount-delete-dialog.component';
import { productDiscountRoute, productDiscountPopupRoute } from './product-discount.route';

const ENTITY_STATES = [...productDiscountRoute, ...productDiscountPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProductDiscountComponent,
    ProductDiscountDetailComponent,
    ProductDiscountUpdateComponent,
    ProductDiscountDeleteDialogComponent,
    ProductDiscountDeletePopupComponent
  ],
  entryComponents: [ProductDiscountDeleteDialogComponent]
})
export class ShopProductDiscountModule {}
