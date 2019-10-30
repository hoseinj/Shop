import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CustomerIp } from 'app/shared/model/customer-ip.model';
import { CustomerIpService } from './customer-ip.service';
import { CustomerIpComponent } from './customer-ip.component';
import { CustomerIpDetailComponent } from './customer-ip-detail.component';
import { CustomerIpUpdateComponent } from './customer-ip-update.component';
import { CustomerIpDeletePopupComponent } from './customer-ip-delete-dialog.component';
import { ICustomerIp } from 'app/shared/model/customer-ip.model';

@Injectable({ providedIn: 'root' })
export class CustomerIpResolve implements Resolve<ICustomerIp> {
  constructor(private service: CustomerIpService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICustomerIp> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CustomerIp>) => response.ok),
        map((customerIp: HttpResponse<CustomerIp>) => customerIp.body)
      );
    }
    return of(new CustomerIp());
  }
}

export const customerIpRoute: Routes = [
  {
    path: '',
    component: CustomerIpComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.customerIp.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustomerIpDetailComponent,
    resolve: {
      customerIp: CustomerIpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerIp.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustomerIpUpdateComponent,
    resolve: {
      customerIp: CustomerIpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerIp.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustomerIpUpdateComponent,
    resolve: {
      customerIp: CustomerIpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerIp.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const customerIpPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CustomerIpDeletePopupComponent,
    resolve: {
      customerIp: CustomerIpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerIp.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
