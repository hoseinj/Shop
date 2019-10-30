import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ShopUserComponent } from './shop-user.component';
import { ShopUserDetailComponent } from './shop-user-detail.component';
import { ShopUserUpdateComponent } from './shop-user-update.component';
import { ShopUserDeletePopupComponent, ShopUserDeleteDialogComponent } from './shop-user-delete-dialog.component';
import { shopUserRoute, shopUserPopupRoute } from './shop-user.route';

const ENTITY_STATES = [...shopUserRoute, ...shopUserPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ShopUserComponent,
    ShopUserDetailComponent,
    ShopUserUpdateComponent,
    ShopUserDeleteDialogComponent,
    ShopUserDeletePopupComponent
  ],
  entryComponents: [ShopUserDeleteDialogComponent]
})
export class ShopShopUserModule {}
