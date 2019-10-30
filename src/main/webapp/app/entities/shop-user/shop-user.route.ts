import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ShopUser } from 'app/shared/model/shop-user.model';
import { ShopUserService } from './shop-user.service';
import { ShopUserComponent } from './shop-user.component';
import { ShopUserDetailComponent } from './shop-user-detail.component';
import { ShopUserUpdateComponent } from './shop-user-update.component';
import { ShopUserDeletePopupComponent } from './shop-user-delete-dialog.component';
import { IShopUser } from 'app/shared/model/shop-user.model';

@Injectable({ providedIn: 'root' })
export class ShopUserResolve implements Resolve<IShopUser> {
  constructor(private service: ShopUserService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IShopUser> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ShopUser>) => response.ok),
        map((shopUser: HttpResponse<ShopUser>) => shopUser.body)
      );
    }
    return of(new ShopUser());
  }
}

export const shopUserRoute: Routes = [
  {
    path: '',
    component: ShopUserComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.shopUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ShopUserDetailComponent,
    resolve: {
      shopUser: ShopUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.shopUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ShopUserUpdateComponent,
    resolve: {
      shopUser: ShopUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.shopUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ShopUserUpdateComponent,
    resolve: {
      shopUser: ShopUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.shopUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const shopUserPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ShopUserDeletePopupComponent,
    resolve: {
      shopUser: ShopUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.shopUser.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
