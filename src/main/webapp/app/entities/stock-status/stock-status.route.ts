import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StockStatus } from 'app/shared/model/stock-status.model';
import { StockStatusService } from './stock-status.service';
import { StockStatusComponent } from './stock-status.component';
import { StockStatusDetailComponent } from './stock-status-detail.component';
import { StockStatusUpdateComponent } from './stock-status-update.component';
import { StockStatusDeletePopupComponent } from './stock-status-delete-dialog.component';
import { IStockStatus } from 'app/shared/model/stock-status.model';

@Injectable({ providedIn: 'root' })
export class StockStatusResolve implements Resolve<IStockStatus> {
  constructor(private service: StockStatusService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IStockStatus> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<StockStatus>) => response.ok),
        map((stockStatus: HttpResponse<StockStatus>) => stockStatus.body)
      );
    }
    return of(new StockStatus());
  }
}

export const stockStatusRoute: Routes = [
  {
    path: '',
    component: StockStatusComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.stockStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: StockStatusDetailComponent,
    resolve: {
      stockStatus: StockStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.stockStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: StockStatusUpdateComponent,
    resolve: {
      stockStatus: StockStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.stockStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: StockStatusUpdateComponent,
    resolve: {
      stockStatus: StockStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.stockStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const stockStatusPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: StockStatusDeletePopupComponent,
    resolve: {
      stockStatus: StockStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.stockStatus.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
