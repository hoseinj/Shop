import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Banner } from 'app/shared/model/banner.model';
import { BannerService } from './banner.service';
import { BannerComponent } from './banner.component';
import { BannerDetailComponent } from './banner-detail.component';
import { BannerUpdateComponent } from './banner-update.component';
import { BannerDeletePopupComponent } from './banner-delete-dialog.component';
import { IBanner } from 'app/shared/model/banner.model';

@Injectable({ providedIn: 'root' })
export class BannerResolve implements Resolve<IBanner> {
  constructor(private service: BannerService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBanner> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Banner>) => response.ok),
        map((banner: HttpResponse<Banner>) => banner.body)
      );
    }
    return of(new Banner());
  }
}

export const bannerRoute: Routes = [
  {
    path: '',
    component: BannerComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.banner.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BannerDetailComponent,
    resolve: {
      banner: BannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.banner.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BannerUpdateComponent,
    resolve: {
      banner: BannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.banner.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BannerUpdateComponent,
    resolve: {
      banner: BannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.banner.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const bannerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BannerDeletePopupComponent,
    resolve: {
      banner: BannerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.banner.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
