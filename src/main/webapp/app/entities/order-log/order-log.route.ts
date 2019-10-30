import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrderLog } from 'app/shared/model/order-log.model';
import { OrderLogService } from './order-log.service';
import { OrderLogComponent } from './order-log.component';
import { OrderLogDetailComponent } from './order-log-detail.component';
import { OrderLogUpdateComponent } from './order-log-update.component';
import { OrderLogDeletePopupComponent } from './order-log-delete-dialog.component';
import { IOrderLog } from 'app/shared/model/order-log.model';

@Injectable({ providedIn: 'root' })
export class OrderLogResolve implements Resolve<IOrderLog> {
  constructor(private service: OrderLogService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOrderLog> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OrderLog>) => response.ok),
        map((orderLog: HttpResponse<OrderLog>) => orderLog.body)
      );
    }
    return of(new OrderLog());
  }
}

export const orderLogRoute: Routes = [
  {
    path: '',
    component: OrderLogComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.orderLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderLogDetailComponent,
    resolve: {
      orderLog: OrderLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderLogUpdateComponent,
    resolve: {
      orderLog: OrderLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderLogUpdateComponent,
    resolve: {
      orderLog: OrderLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const orderLogPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OrderLogDeletePopupComponent,
    resolve: {
      orderLog: OrderLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderLog.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
