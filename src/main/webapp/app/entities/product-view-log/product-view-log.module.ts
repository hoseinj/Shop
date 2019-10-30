import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ProductViewLogComponent } from './product-view-log.component';
import { ProductViewLogDetailComponent } from './product-view-log-detail.component';
import { ProductViewLogUpdateComponent } from './product-view-log-update.component';
import { ProductViewLogDeletePopupComponent, ProductViewLogDeleteDialogComponent } from './product-view-log-delete-dialog.component';
import { productViewLogRoute, productViewLogPopupRoute } from './product-view-log.route';

const ENTITY_STATES = [...productViewLogRoute, ...productViewLogPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProductViewLogComponent,
    ProductViewLogDetailComponent,
    ProductViewLogUpdateComponent,
    ProductViewLogDeleteDialogComponent,
    ProductViewLogDeletePopupComponent
  ],
  entryComponents: [ProductViewLogDeleteDialogComponent]
})
export class ShopProductViewLogModule {}
