import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrderStatus } from 'app/shared/model/order-status.model';
import { OrderStatusService } from './order-status.service';
import { OrderStatusComponent } from './order-status.component';
import { OrderStatusDetailComponent } from './order-status-detail.component';
import { OrderStatusUpdateComponent } from './order-status-update.component';
import { OrderStatusDeletePopupComponent } from './order-status-delete-dialog.component';
import { IOrderStatus } from 'app/shared/model/order-status.model';

@Injectable({ providedIn: 'root' })
export class OrderStatusResolve implements Resolve<IOrderStatus> {
  constructor(private service: OrderStatusService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOrderStatus> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OrderStatus>) => response.ok),
        map((orderStatus: HttpResponse<OrderStatus>) => orderStatus.body)
      );
    }
    return of(new OrderStatus());
  }
}

export const orderStatusRoute: Routes = [
  {
    path: '',
    component: OrderStatusComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.orderStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderStatusDetailComponent,
    resolve: {
      orderStatus: OrderStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderStatusUpdateComponent,
    resolve: {
      orderStatus: OrderStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderStatusUpdateComponent,
    resolve: {
      orderStatus: OrderStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const orderStatusPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OrderStatusDeletePopupComponent,
    resolve: {
      orderStatus: OrderStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderStatus.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
