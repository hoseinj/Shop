import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CustomerTransaction } from 'app/shared/model/customer-transaction.model';
import { CustomerTransactionService } from './customer-transaction.service';
import { CustomerTransactionComponent } from './customer-transaction.component';
import { CustomerTransactionDetailComponent } from './customer-transaction-detail.component';
import { CustomerTransactionUpdateComponent } from './customer-transaction-update.component';
import { CustomerTransactionDeletePopupComponent } from './customer-transaction-delete-dialog.component';
import { ICustomerTransaction } from 'app/shared/model/customer-transaction.model';

@Injectable({ providedIn: 'root' })
export class CustomerTransactionResolve implements Resolve<ICustomerTransaction> {
  constructor(private service: CustomerTransactionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICustomerTransaction> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CustomerTransaction>) => response.ok),
        map((customerTransaction: HttpResponse<CustomerTransaction>) => customerTransaction.body)
      );
    }
    return of(new CustomerTransaction());
  }
}

export const customerTransactionRoute: Routes = [
  {
    path: '',
    component: CustomerTransactionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.customerTransaction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustomerTransactionDetailComponent,
    resolve: {
      customerTransaction: CustomerTransactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerTransaction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustomerTransactionUpdateComponent,
    resolve: {
      customerTransaction: CustomerTransactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerTransaction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustomerTransactionUpdateComponent,
    resolve: {
      customerTransaction: CustomerTransactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerTransaction.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const customerTransactionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CustomerTransactionDeletePopupComponent,
    resolve: {
      customerTransaction: CustomerTransactionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerTransaction.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
