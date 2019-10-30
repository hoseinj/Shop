import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { StockStatusComponent } from './stock-status.component';
import { StockStatusDetailComponent } from './stock-status-detail.component';
import { StockStatusUpdateComponent } from './stock-status-update.component';
import { StockStatusDeletePopupComponent, StockStatusDeleteDialogComponent } from './stock-status-delete-dialog.component';
import { stockStatusRoute, stockStatusPopupRoute } from './stock-status.route';

const ENTITY_STATES = [...stockStatusRoute, ...stockStatusPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    StockStatusComponent,
    StockStatusDetailComponent,
    StockStatusUpdateComponent,
    StockStatusDeleteDialogComponent,
    StockStatusDeletePopupComponent
  ],
  entryComponents: [StockStatusDeleteDialogComponent]
})
export class ShopStockStatusModule {}
