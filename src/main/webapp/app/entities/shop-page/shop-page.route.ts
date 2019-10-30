import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ShopPage } from 'app/shared/model/shop-page.model';
import { ShopPageService } from './shop-page.service';
import { ShopPageComponent } from './shop-page.component';
import { ShopPageDetailComponent } from './shop-page-detail.component';
import { ShopPageUpdateComponent } from './shop-page-update.component';
import { ShopPageDeletePopupComponent } from './shop-page-delete-dialog.component';
import { IShopPage } from 'app/shared/model/shop-page.model';

@Injectable({ providedIn: 'root' })
export class ShopPageResolve implements Resolve<IShopPage> {
  constructor(private service: ShopPageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IShopPage> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ShopPage>) => response.ok),
        map((shopPage: HttpResponse<ShopPage>) => shopPage.body)
      );
    }
    return of(new ShopPage());
  }
}

export const shopPageRoute: Routes = [
  {
    path: '',
    component: ShopPageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.shopPage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ShopPageDetailComponent,
    resolve: {
      shopPage: ShopPageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.shopPage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ShopPageUpdateComponent,
    resolve: {
      shopPage: ShopPageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.shopPage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ShopPageUpdateComponent,
    resolve: {
      shopPage: ShopPageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.shopPage.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const shopPagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ShopPageDeletePopupComponent,
    resolve: {
      shopPage: ShopPageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.shopPage.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
