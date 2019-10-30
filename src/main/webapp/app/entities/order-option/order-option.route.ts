import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrderOption } from 'app/shared/model/order-option.model';
import { OrderOptionService } from './order-option.service';
import { OrderOptionComponent } from './order-option.component';
import { OrderOptionDetailComponent } from './order-option-detail.component';
import { OrderOptionUpdateComponent } from './order-option-update.component';
import { OrderOptionDeletePopupComponent } from './order-option-delete-dialog.component';
import { IOrderOption } from 'app/shared/model/order-option.model';

@Injectable({ providedIn: 'root' })
export class OrderOptionResolve implements Resolve<IOrderOption> {
  constructor(private service: OrderOptionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOrderOption> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OrderOption>) => response.ok),
        map((orderOption: HttpResponse<OrderOption>) => orderOption.body)
      );
    }
    return of(new OrderOption());
  }
}

export const orderOptionRoute: Routes = [
  {
    path: '',
    component: OrderOptionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.orderOption.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderOptionDetailComponent,
    resolve: {
      orderOption: OrderOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderOption.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderOptionUpdateComponent,
    resolve: {
      orderOption: OrderOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderOption.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderOptionUpdateComponent,
    resolve: {
      orderOption: OrderOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderOption.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const orderOptionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OrderOptionDeletePopupComponent,
    resolve: {
      orderOption: OrderOptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderOption.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
