import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { GeoZoneComponent } from './geo-zone.component';
import { GeoZoneDetailComponent } from './geo-zone-detail.component';
import { GeoZoneUpdateComponent } from './geo-zone-update.component';
import { GeoZoneDeletePopupComponent, GeoZoneDeleteDialogComponent } from './geo-zone-delete-dialog.component';
import { geoZoneRoute, geoZonePopupRoute } from './geo-zone.route';

const ENTITY_STATES = [...geoZoneRoute, ...geoZonePopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GeoZoneComponent,
    GeoZoneDetailComponent,
    GeoZoneUpdateComponent,
    GeoZoneDeleteDialogComponent,
    GeoZoneDeletePopupComponent
  ],
  entryComponents: [GeoZoneDeleteDialogComponent]
})
export class ShopGeoZoneModule {}
