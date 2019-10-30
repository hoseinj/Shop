import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CustomerGroup } from 'app/shared/model/customer-group.model';
import { CustomerGroupService } from './customer-group.service';
import { CustomerGroupComponent } from './customer-group.component';
import { CustomerGroupDetailComponent } from './customer-group-detail.component';
import { CustomerGroupUpdateComponent } from './customer-group-update.component';
import { CustomerGroupDeletePopupComponent } from './customer-group-delete-dialog.component';
import { ICustomerGroup } from 'app/shared/model/customer-group.model';

@Injectable({ providedIn: 'root' })
export class CustomerGroupResolve implements Resolve<ICustomerGroup> {
  constructor(private service: CustomerGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICustomerGroup> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CustomerGroup>) => response.ok),
        map((customerGroup: HttpResponse<CustomerGroup>) => customerGroup.body)
      );
    }
    return of(new CustomerGroup());
  }
}

export const customerGroupRoute: Routes = [
  {
    path: '',
    component: CustomerGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.customerGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustomerGroupDetailComponent,
    resolve: {
      customerGroup: CustomerGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustomerGroupUpdateComponent,
    resolve: {
      customerGroup: CustomerGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustomerGroupUpdateComponent,
    resolve: {
      customerGroup: CustomerGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const customerGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CustomerGroupDeletePopupComponent,
    resolve: {
      customerGroup: CustomerGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerGroup.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
