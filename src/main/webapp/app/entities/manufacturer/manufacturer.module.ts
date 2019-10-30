import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ManufacturerComponent } from './manufacturer.component';
import { ManufacturerDetailComponent } from './manufacturer-detail.component';
import { ManufacturerUpdateComponent } from './manufacturer-update.component';
import { ManufacturerDeletePopupComponent, ManufacturerDeleteDialogComponent } from './manufacturer-delete-dialog.component';
import { manufacturerRoute, manufacturerPopupRoute } from './manufacturer.route';

const ENTITY_STATES = [...manufacturerRoute, ...manufacturerPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ManufacturerComponent,
    ManufacturerDetailComponent,
    ManufacturerUpdateComponent,
    ManufacturerDeleteDialogComponent,
    ManufacturerDeletePopupComponent
  ],
  entryComponents: [ManufacturerDeleteDialogComponent]
})
export class ShopManufacturerModule {}
