import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BannerGroup } from 'app/shared/model/banner-group.model';
import { BannerGroupService } from './banner-group.service';
import { BannerGroupComponent } from './banner-group.component';
import { BannerGroupDetailComponent } from './banner-group-detail.component';
import { BannerGroupUpdateComponent } from './banner-group-update.component';
import { BannerGroupDeletePopupComponent } from './banner-group-delete-dialog.component';
import { IBannerGroup } from 'app/shared/model/banner-group.model';

@Injectable({ providedIn: 'root' })
export class BannerGroupResolve implements Resolve<IBannerGroup> {
  constructor(private service: BannerGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBannerGroup> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BannerGroup>) => response.ok),
        map((bannerGroup: HttpResponse<BannerGroup>) => bannerGroup.body)
      );
    }
    return of(new BannerGroup());
  }
}

export const bannerGroupRoute: Routes = [
  {
    path: '',
    component: BannerGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.bannerGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BannerGroupDetailComponent,
    resolve: {
      bannerGroup: BannerGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.bannerGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BannerGroupUpdateComponent,
    resolve: {
      bannerGroup: BannerGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.bannerGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BannerGroupUpdateComponent,
    resolve: {
      bannerGroup: BannerGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.bannerGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const bannerGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BannerGroupDeletePopupComponent,
    resolve: {
      bannerGroup: BannerGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.bannerGroup.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
