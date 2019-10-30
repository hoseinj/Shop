import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProductViewLog } from 'app/shared/model/product-view-log.model';
import { ProductViewLogService } from './product-view-log.service';
import { ProductViewLogComponent } from './product-view-log.component';
import { ProductViewLogDetailComponent } from './product-view-log-detail.component';
import { ProductViewLogUpdateComponent } from './product-view-log-update.component';
import { ProductViewLogDeletePopupComponent } from './product-view-log-delete-dialog.component';
import { IProductViewLog } from 'app/shared/model/product-view-log.model';

@Injectable({ providedIn: 'root' })
export class ProductViewLogResolve implements Resolve<IProductViewLog> {
  constructor(private service: ProductViewLogService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IProductViewLog> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ProductViewLog>) => response.ok),
        map((productViewLog: HttpResponse<ProductViewLog>) => productViewLog.body)
      );
    }
    return of(new ProductViewLog());
  }
}

export const productViewLogRoute: Routes = [
  {
    path: '',
    component: ProductViewLogComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.productViewLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductViewLogDetailComponent,
    resolve: {
      productViewLog: ProductViewLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productViewLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductViewLogUpdateComponent,
    resolve: {
      productViewLog: ProductViewLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productViewLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductViewLogUpdateComponent,
    resolve: {
      productViewLog: ProductViewLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productViewLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const productViewLogPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProductViewLogDeletePopupComponent,
    resolve: {
      productViewLog: ProductViewLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productViewLog.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
