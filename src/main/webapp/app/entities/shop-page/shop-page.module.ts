import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ShopPageComponent } from './shop-page.component';
import { ShopPageDetailComponent } from './shop-page-detail.component';
import { ShopPageUpdateComponent } from './shop-page-update.component';
import { ShopPageDeletePopupComponent, ShopPageDeleteDialogComponent } from './shop-page-delete-dialog.component';
import { shopPageRoute, shopPagePopupRoute } from './shop-page.route';

const ENTITY_STATES = [...shopPageRoute, ...shopPagePopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ShopPageComponent,
    ShopPageDetailComponent,
    ShopPageUpdateComponent,
    ShopPageDeleteDialogComponent,
    ShopPageDeletePopupComponent
  ],
  entryComponents: [ShopPageDeleteDialogComponent]
})
export class ShopShopPageModule {}
