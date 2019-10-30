import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { AccessTokenComponent } from './access-token.component';
import { AccessTokenDetailComponent } from './access-token-detail.component';
import { AccessTokenUpdateComponent } from './access-token-update.component';
import { AccessTokenDeletePopupComponent, AccessTokenDeleteDialogComponent } from './access-token-delete-dialog.component';
import { accessTokenRoute, accessTokenPopupRoute } from './access-token.route';

const ENTITY_STATES = [...accessTokenRoute, ...accessTokenPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AccessTokenComponent,
    AccessTokenDetailComponent,
    AccessTokenUpdateComponent,
    AccessTokenDeleteDialogComponent,
    AccessTokenDeletePopupComponent
  ],
  entryComponents: [AccessTokenDeleteDialogComponent]
})
export class ShopAccessTokenModule {}
