import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrderTotal } from 'app/shared/model/order-total.model';
import { OrderTotalService } from './order-total.service';
import { OrderTotalComponent } from './order-total.component';
import { OrderTotalDetailComponent } from './order-total-detail.component';
import { OrderTotalUpdateComponent } from './order-total-update.component';
import { OrderTotalDeletePopupComponent } from './order-total-delete-dialog.component';
import { IOrderTotal } from 'app/shared/model/order-total.model';

@Injectable({ providedIn: 'root' })
export class OrderTotalResolve implements Resolve<IOrderTotal> {
  constructor(private service: OrderTotalService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOrderTotal> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OrderTotal>) => response.ok),
        map((orderTotal: HttpResponse<OrderTotal>) => orderTotal.body)
      );
    }
    return of(new OrderTotal());
  }
}

export const orderTotalRoute: Routes = [
  {
    path: '',
    component: OrderTotalComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.orderTotal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderTotalDetailComponent,
    resolve: {
      orderTotal: OrderTotalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderTotal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderTotalUpdateComponent,
    resolve: {
      orderTotal: OrderTotalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderTotal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderTotalUpdateComponent,
    resolve: {
      orderTotal: OrderTotalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderTotal.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const orderTotalPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OrderTotalDeletePopupComponent,
    resolve: {
      orderTotal: OrderTotalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderTotal.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
