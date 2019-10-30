import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Setting } from 'app/shared/model/setting.model';
import { SettingService } from './setting.service';
import { SettingComponent } from './setting.component';
import { SettingDetailComponent } from './setting-detail.component';
import { SettingUpdateComponent } from './setting-update.component';
import { SettingDeletePopupComponent } from './setting-delete-dialog.component';
import { ISetting } from 'app/shared/model/setting.model';

@Injectable({ providedIn: 'root' })
export class SettingResolve implements Resolve<ISetting> {
  constructor(private service: SettingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISetting> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Setting>) => response.ok),
        map((setting: HttpResponse<Setting>) => setting.body)
      );
    }
    return of(new Setting());
  }
}

export const settingRoute: Routes = [
  {
    path: '',
    component: SettingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.setting.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SettingDetailComponent,
    resolve: {
      setting: SettingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.setting.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SettingUpdateComponent,
    resolve: {
      setting: SettingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.setting.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SettingUpdateComponent,
    resolve: {
      setting: SettingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.setting.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const settingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SettingDeletePopupComponent,
    resolve: {
      setting: SettingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.setting.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
