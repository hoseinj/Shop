import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { OrderOptionComponent } from './order-option.component';
import { OrderOptionDetailComponent } from './order-option-detail.component';
import { OrderOptionUpdateComponent } from './order-option-update.component';
import { OrderOptionDeletePopupComponent, OrderOptionDeleteDialogComponent } from './order-option-delete-dialog.component';
import { orderOptionRoute, orderOptionPopupRoute } from './order-option.route';

const ENTITY_STATES = [...orderOptionRoute, ...orderOptionPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OrderOptionComponent,
    OrderOptionDetailComponent,
    OrderOptionUpdateComponent,
    OrderOptionDeleteDialogComponent,
    OrderOptionDeletePopupComponent
  ],
  entryComponents: [OrderOptionDeleteDialogComponent]
})
export class ShopOrderOptionModule {}
