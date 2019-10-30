import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { OrderStatusComponent } from './order-status.component';
import { OrderStatusDetailComponent } from './order-status-detail.component';
import { OrderStatusUpdateComponent } from './order-status-update.component';
import { OrderStatusDeletePopupComponent, OrderStatusDeleteDialogComponent } from './order-status-delete-dialog.component';
import { orderStatusRoute, orderStatusPopupRoute } from './order-status.route';

const ENTITY_STATES = [...orderStatusRoute, ...orderStatusPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OrderStatusComponent,
    OrderStatusDetailComponent,
    OrderStatusUpdateComponent,
    OrderStatusDeleteDialogComponent,
    OrderStatusDeletePopupComponent
  ],
  entryComponents: [OrderStatusDeleteDialogComponent]
})
export class ShopOrderStatusModule {}
