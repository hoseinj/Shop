import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { PorductDescriptionComponent } from './porduct-description.component';
import { PorductDescriptionDetailComponent } from './porduct-description-detail.component';
import { PorductDescriptionUpdateComponent } from './porduct-description-update.component';
import {
  PorductDescriptionDeletePopupComponent,
  PorductDescriptionDeleteDialogComponent
} from './porduct-description-delete-dialog.component';
import { porductDescriptionRoute, porductDescriptionPopupRoute } from './porduct-description.route';

const ENTITY_STATES = [...porductDescriptionRoute, ...porductDescriptionPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PorductDescriptionComponent,
    PorductDescriptionDetailComponent,
    PorductDescriptionUpdateComponent,
    PorductDescriptionDeleteDialogComponent,
    PorductDescriptionDeletePopupComponent
  ],
  entryComponents: [PorductDescriptionDeleteDialogComponent]
})
export class ShopPorductDescriptionModule {}
