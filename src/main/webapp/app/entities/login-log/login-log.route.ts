import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LoginLog } from 'app/shared/model/login-log.model';
import { LoginLogService } from './login-log.service';
import { LoginLogComponent } from './login-log.component';
import { LoginLogDetailComponent } from './login-log-detail.component';
import { LoginLogUpdateComponent } from './login-log-update.component';
import { LoginLogDeletePopupComponent } from './login-log-delete-dialog.component';
import { ILoginLog } from 'app/shared/model/login-log.model';

@Injectable({ providedIn: 'root' })
export class LoginLogResolve implements Resolve<ILoginLog> {
  constructor(private service: LoginLogService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ILoginLog> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<LoginLog>) => response.ok),
        map((loginLog: HttpResponse<LoginLog>) => loginLog.body)
      );
    }
    return of(new LoginLog());
  }
}

export const loginLogRoute: Routes = [
  {
    path: '',
    component: LoginLogComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.loginLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LoginLogDetailComponent,
    resolve: {
      loginLog: LoginLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.loginLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LoginLogUpdateComponent,
    resolve: {
      loginLog: LoginLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.loginLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LoginLogUpdateComponent,
    resolve: {
      loginLog: LoginLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.loginLog.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const loginLogPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: LoginLogDeletePopupComponent,
    resolve: {
      loginLog: LoginLogResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.loginLog.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
