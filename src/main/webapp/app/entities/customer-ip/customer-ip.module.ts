import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { CustomerIpComponent } from './customer-ip.component';
import { CustomerIpDetailComponent } from './customer-ip-detail.component';
import { CustomerIpUpdateComponent } from './customer-ip-update.component';
import { CustomerIpDeletePopupComponent, CustomerIpDeleteDialogComponent } from './customer-ip-delete-dialog.component';
import { customerIpRoute, customerIpPopupRoute } from './customer-ip.route';

const ENTITY_STATES = [...customerIpRoute, ...customerIpPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CustomerIpComponent,
    CustomerIpDetailComponent,
    CustomerIpUpdateComponent,
    CustomerIpDeleteDialogComponent,
    CustomerIpDeletePopupComponent
  ],
  entryComponents: [CustomerIpDeleteDialogComponent]
})
export class ShopCustomerIpModule {}
