import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrderProduct } from 'app/shared/model/order-product.model';
import { OrderProductService } from './order-product.service';
import { OrderProductComponent } from './order-product.component';
import { OrderProductDetailComponent } from './order-product-detail.component';
import { OrderProductUpdateComponent } from './order-product-update.component';
import { OrderProductDeletePopupComponent } from './order-product-delete-dialog.component';
import { IOrderProduct } from 'app/shared/model/order-product.model';

@Injectable({ providedIn: 'root' })
export class OrderProductResolve implements Resolve<IOrderProduct> {
  constructor(private service: OrderProductService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOrderProduct> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OrderProduct>) => response.ok),
        map((orderProduct: HttpResponse<OrderProduct>) => orderProduct.body)
      );
    }
    return of(new OrderProduct());
  }
}

export const orderProductRoute: Routes = [
  {
    path: '',
    component: OrderProductComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.orderProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OrderProductDetailComponent,
    resolve: {
      orderProduct: OrderProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OrderProductUpdateComponent,
    resolve: {
      orderProduct: OrderProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OrderProductUpdateComponent,
    resolve: {
      orderProduct: OrderProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const orderProductPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OrderProductDeletePopupComponent,
    resolve: {
      orderProduct: OrderProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.orderProduct.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
