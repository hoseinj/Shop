import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { CustomerGroupComponent } from './customer-group.component';
import { CustomerGroupDetailComponent } from './customer-group-detail.component';
import { CustomerGroupUpdateComponent } from './customer-group-update.component';
import { CustomerGroupDeletePopupComponent, CustomerGroupDeleteDialogComponent } from './customer-group-delete-dialog.component';
import { customerGroupRoute, customerGroupPopupRoute } from './customer-group.route';

const ENTITY_STATES = [...customerGroupRoute, ...customerGroupPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CustomerGroupComponent,
    CustomerGroupDetailComponent,
    CustomerGroupUpdateComponent,
    CustomerGroupDeleteDialogComponent,
    CustomerGroupDeletePopupComponent
  ],
  entryComponents: [CustomerGroupDeleteDialogComponent]
})
export class ShopCustomerGroupModule {}
