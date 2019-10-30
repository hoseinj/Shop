import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { BannerGroupComponent } from './banner-group.component';
import { BannerGroupDetailComponent } from './banner-group-detail.component';
import { BannerGroupUpdateComponent } from './banner-group-update.component';
import { BannerGroupDeletePopupComponent, BannerGroupDeleteDialogComponent } from './banner-group-delete-dialog.component';
import { bannerGroupRoute, bannerGroupPopupRoute } from './banner-group.route';

const ENTITY_STATES = [...bannerGroupRoute, ...bannerGroupPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BannerGroupComponent,
    BannerGroupDetailComponent,
    BannerGroupUpdateComponent,
    BannerGroupDeleteDialogComponent,
    BannerGroupDeletePopupComponent
  ],
  entryComponents: [BannerGroupDeleteDialogComponent]
})
export class ShopBannerGroupModule {}
