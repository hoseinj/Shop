import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { PageGroupComponent } from './page-group.component';
import { PageGroupDetailComponent } from './page-group-detail.component';
import { PageGroupUpdateComponent } from './page-group-update.component';
import { PageGroupDeletePopupComponent, PageGroupDeleteDialogComponent } from './page-group-delete-dialog.component';
import { pageGroupRoute, pageGroupPopupRoute } from './page-group.route';

const ENTITY_STATES = [...pageGroupRoute, ...pageGroupPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PageGroupComponent,
    PageGroupDetailComponent,
    PageGroupUpdateComponent,
    PageGroupDeleteDialogComponent,
    PageGroupDeletePopupComponent
  ],
  entryComponents: [PageGroupDeleteDialogComponent]
})
export class ShopPageGroupModule {}
