import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { OrderLogComponent } from './order-log.component';
import { OrderLogDetailComponent } from './order-log-detail.component';
import { OrderLogUpdateComponent } from './order-log-update.component';
import { OrderLogDeletePopupComponent, OrderLogDeleteDialogComponent } from './order-log-delete-dialog.component';
import { orderLogRoute, orderLogPopupRoute } from './order-log.route';

const ENTITY_STATES = [...orderLogRoute, ...orderLogPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OrderLogComponent,
    OrderLogDetailComponent,
    OrderLogUpdateComponent,
    OrderLogDeleteDialogComponent,
    OrderLogDeletePopupComponent
  ],
  entryComponents: [OrderLogDeleteDialogComponent]
})
export class ShopOrderLogModule {}
