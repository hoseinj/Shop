import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ProductImageComponent } from './product-image.component';
import { ProductImageDetailComponent } from './product-image-detail.component';
import { ProductImageUpdateComponent } from './product-image-update.component';
import { ProductImageDeletePopupComponent, ProductImageDeleteDialogComponent } from './product-image-delete-dialog.component';
import { productImageRoute, productImagePopupRoute } from './product-image.route';

const ENTITY_STATES = [...productImageRoute, ...productImagePopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProductImageComponent,
    ProductImageDetailComponent,
    ProductImageUpdateComponent,
    ProductImageDeleteDialogComponent,
    ProductImageDeletePopupComponent
  ],
  entryComponents: [ProductImageDeleteDialogComponent]
})
export class ShopProductImageModule {}
