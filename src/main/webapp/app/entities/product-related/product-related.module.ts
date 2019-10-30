import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ProductRelatedComponent } from './product-related.component';
import { ProductRelatedDetailComponent } from './product-related-detail.component';
import { ProductRelatedUpdateComponent } from './product-related-update.component';
import { ProductRelatedDeletePopupComponent, ProductRelatedDeleteDialogComponent } from './product-related-delete-dialog.component';
import { productRelatedRoute, productRelatedPopupRoute } from './product-related.route';

const ENTITY_STATES = [...productRelatedRoute, ...productRelatedPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProductRelatedComponent,
    ProductRelatedDetailComponent,
    ProductRelatedUpdateComponent,
    ProductRelatedDeleteDialogComponent,
    ProductRelatedDeletePopupComponent
  ],
  entryComponents: [ProductRelatedDeleteDialogComponent]
})
export class ShopProductRelatedModule {}
