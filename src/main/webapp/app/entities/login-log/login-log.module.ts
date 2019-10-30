import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { LoginLogComponent } from './login-log.component';
import { LoginLogDetailComponent } from './login-log-detail.component';
import { LoginLogUpdateComponent } from './login-log-update.component';
import { LoginLogDeletePopupComponent, LoginLogDeleteDialogComponent } from './login-log-delete-dialog.component';
import { loginLogRoute, loginLogPopupRoute } from './login-log.route';

const ENTITY_STATES = [...loginLogRoute, ...loginLogPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    LoginLogComponent,
    LoginLogDetailComponent,
    LoginLogUpdateComponent,
    LoginLogDeleteDialogComponent,
    LoginLogDeletePopupComponent
  ],
  entryComponents: [LoginLogDeleteDialogComponent]
})
export class ShopLoginLogModule {}
