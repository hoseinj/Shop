import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { BannerComponent } from './banner.component';
import { BannerDetailComponent } from './banner-detail.component';
import { BannerUpdateComponent } from './banner-update.component';
import { BannerDeletePopupComponent, BannerDeleteDialogComponent } from './banner-delete-dialog.component';
import { bannerRoute, bannerPopupRoute } from './banner.route';

const ENTITY_STATES = [...bannerRoute, ...bannerPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [BannerComponent, BannerDetailComponent, BannerUpdateComponent, BannerDeleteDialogComponent, BannerDeletePopupComponent],
  entryComponents: [BannerDeleteDialogComponent]
})
export class ShopBannerModule {}
