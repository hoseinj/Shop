import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { BannerImageDescriptionComponent } from './banner-image-description.component';
import { BannerImageDescriptionDetailComponent } from './banner-image-description-detail.component';
import { BannerImageDescriptionUpdateComponent } from './banner-image-description-update.component';
import {
  BannerImageDescriptionDeletePopupComponent,
  BannerImageDescriptionDeleteDialogComponent
} from './banner-image-description-delete-dialog.component';
import { bannerImageDescriptionRoute, bannerImageDescriptionPopupRoute } from './banner-image-description.route';

const ENTITY_STATES = [...bannerImageDescriptionRoute, ...bannerImageDescriptionPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BannerImageDescriptionComponent,
    BannerImageDescriptionDetailComponent,
    BannerImageDescriptionUpdateComponent,
    BannerImageDescriptionDeleteDialogComponent,
    BannerImageDescriptionDeletePopupComponent
  ],
  entryComponents: [BannerImageDescriptionDeleteDialogComponent]
})
export class ShopBannerImageDescriptionModule {}
