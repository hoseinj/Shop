import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AccessToken } from 'app/shared/model/access-token.model';
import { AccessTokenService } from './access-token.service';
import { AccessTokenComponent } from './access-token.component';
import { AccessTokenDetailComponent } from './access-token-detail.component';
import { AccessTokenUpdateComponent } from './access-token-update.component';
import { AccessTokenDeletePopupComponent } from './access-token-delete-dialog.component';
import { IAccessToken } from 'app/shared/model/access-token.model';

@Injectable({ providedIn: 'root' })
export class AccessTokenResolve implements Resolve<IAccessToken> {
  constructor(private service: AccessTokenService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAccessToken> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AccessToken>) => response.ok),
        map((accessToken: HttpResponse<AccessToken>) => accessToken.body)
      );
    }
    return of(new AccessToken());
  }
}

export const accessTokenRoute: Routes = [
  {
    path: '',
    component: AccessTokenComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.accessToken.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AccessTokenDetailComponent,
    resolve: {
      accessToken: AccessTokenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.accessToken.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AccessTokenUpdateComponent,
    resolve: {
      accessToken: AccessTokenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.accessToken.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AccessTokenUpdateComponent,
    resolve: {
      accessToken: AccessTokenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.accessToken.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const accessTokenPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AccessTokenDeletePopupComponent,
    resolve: {
      accessToken: AccessTokenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.accessToken.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
