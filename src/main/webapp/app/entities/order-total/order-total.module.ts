import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { OrderTotalComponent } from './order-total.component';
import { OrderTotalDetailComponent } from './order-total-detail.component';
import { OrderTotalUpdateComponent } from './order-total-update.component';
import { OrderTotalDeletePopupComponent, OrderTotalDeleteDialogComponent } from './order-total-delete-dialog.component';
import { orderTotalRoute, orderTotalPopupRoute } from './order-total.route';

const ENTITY_STATES = [...orderTotalRoute, ...orderTotalPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OrderTotalComponent,
    OrderTotalDetailComponent,
    OrderTotalUpdateComponent,
    OrderTotalDeleteDialogComponent,
    OrderTotalDeletePopupComponent
  ],
  entryComponents: [OrderTotalDeleteDialogComponent]
})
export class ShopOrderTotalModule {}
