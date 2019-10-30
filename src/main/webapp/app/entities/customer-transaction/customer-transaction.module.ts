import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { CustomerTransactionComponent } from './customer-transaction.component';
import { CustomerTransactionDetailComponent } from './customer-transaction-detail.component';
import { CustomerTransactionUpdateComponent } from './customer-transaction-update.component';
import {
  CustomerTransactionDeletePopupComponent,
  CustomerTransactionDeleteDialogComponent
} from './customer-transaction-delete-dialog.component';
import { customerTransactionRoute, customerTransactionPopupRoute } from './customer-transaction.route';

const ENTITY_STATES = [...customerTransactionRoute, ...customerTransactionPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CustomerTransactionComponent,
    CustomerTransactionDetailComponent,
    CustomerTransactionUpdateComponent,
    CustomerTransactionDeleteDialogComponent,
    CustomerTransactionDeletePopupComponent
  ],
  entryComponents: [CustomerTransactionDeleteDialogComponent]
})
export class ShopCustomerTransactionModule {}
