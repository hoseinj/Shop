import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { OrderProductComponent } from './order-product.component';
import { OrderProductDetailComponent } from './order-product-detail.component';
import { OrderProductUpdateComponent } from './order-product-update.component';
import { OrderProductDeletePopupComponent, OrderProductDeleteDialogComponent } from './order-product-delete-dialog.component';
import { orderProductRoute, orderProductPopupRoute } from './order-product.route';

const ENTITY_STATES = [...orderProductRoute, ...orderProductPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OrderProductComponent,
    OrderProductDetailComponent,
    OrderProductUpdateComponent,
    OrderProductDeleteDialogComponent,
    OrderProductDeletePopupComponent
  ],
  entryComponents: [OrderProductDeleteDialogComponent]
})
export class ShopOrderProductModule {}
