import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { SettingComponent } from './setting.component';
import { SettingDetailComponent } from './setting-detail.component';
import { SettingUpdateComponent } from './setting-update.component';
import { SettingDeletePopupComponent, SettingDeleteDialogComponent } from './setting-delete-dialog.component';
import { settingRoute, settingPopupRoute } from './setting.route';

const ENTITY_STATES = [...settingRoute, ...settingPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SettingComponent,
    SettingDetailComponent,
    SettingUpdateComponent,
    SettingDeleteDialogComponent,
    SettingDeletePopupComponent
  ],
  entryComponents: [SettingDeleteDialogComponent]
})
export class ShopSettingModule {}
