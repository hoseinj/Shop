import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { ZoneComponent } from './zone.component';
import { ZoneDetailComponent } from './zone-detail.component';
import { ZoneUpdateComponent } from './zone-update.component';
import { ZoneDeletePopupComponent, ZoneDeleteDialogComponent } from './zone-delete-dialog.component';
import { zoneRoute, zonePopupRoute } from './zone.route';

const ENTITY_STATES = [...zoneRoute, ...zonePopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [ZoneComponent, ZoneDetailComponent, ZoneUpdateComponent, ZoneDeleteDialogComponent, ZoneDeletePopupComponent],
  entryComponents: [ZoneDeleteDialogComponent]
})
export class ShopZoneModule {}
