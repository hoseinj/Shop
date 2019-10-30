import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { OrderHistoryComponent } from './order-history.component';
import { OrderHistoryDetailComponent } from './order-history-detail.component';
import { OrderHistoryUpdateComponent } from './order-history-update.component';
import { OrderHistoryDeletePopupComponent, OrderHistoryDeleteDialogComponent } from './order-history-delete-dialog.component';
import { orderHistoryRoute, orderHistoryPopupRoute } from './order-history.route';

const ENTITY_STATES = [...orderHistoryRoute, ...orderHistoryPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OrderHistoryComponent,
    OrderHistoryDetailComponent,
    OrderHistoryUpdateComponent,
    OrderHistoryDeleteDialogComponent,
    OrderHistoryDeletePopupComponent
  ],
  entryComponents: [OrderHistoryDeleteDialogComponent]
})
export class ShopOrderHistoryModule {}
