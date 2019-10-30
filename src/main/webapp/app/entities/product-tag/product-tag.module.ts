import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ProductTagComponent } from './product-tag.component';
import { ProductTagDetailComponent } from './product-tag-detail.component';
import { ProductTagUpdateComponent } from './product-tag-update.component';
import { ProductTagDeletePopupComponent, ProductTagDeleteDialogComponent } from './product-tag-delete-dialog.component';
import { productTagRoute, productTagPopupRoute } from './product-tag.route';

const ENTITY_STATES = [...productTagRoute, ...productTagPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProductTagComponent,
    ProductTagDetailComponent,
    ProductTagUpdateComponent,
    ProductTagDeleteDialogComponent,
    ProductTagDeletePopupComponent
  ],
  entryComponents: [ProductTagDeleteDialogComponent]
})
export class ShopProductTagModule {}
