import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BannerImageDescription } from 'app/shared/model/banner-image-description.model';
import { BannerImageDescriptionService } from './banner-image-description.service';
import { BannerImageDescriptionComponent } from './banner-image-description.component';
import { BannerImageDescriptionDetailComponent } from './banner-image-description-detail.component';
import { BannerImageDescriptionUpdateComponent } from './banner-image-description-update.component';
import { BannerImageDescriptionDeletePopupComponent } from './banner-image-description-delete-dialog.component';
import { IBannerImageDescription } from 'app/shared/model/banner-image-description.model';

@Injectable({ providedIn: 'root' })
export class BannerImageDescriptionResolve implements Resolve<IBannerImageDescription> {
  constructor(private service: BannerImageDescriptionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBannerImageDescription> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BannerImageDescription>) => response.ok),
        map((bannerImageDescription: HttpResponse<BannerImageDescription>) => bannerImageDescription.body)
      );
    }
    return of(new BannerImageDescription());
  }
}

export const bannerImageDescriptionRoute: Routes = [
  {
    path: '',
    component: BannerImageDescriptionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.bannerImageDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BannerImageDescriptionDetailComponent,
    resolve: {
      bannerImageDescription: BannerImageDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.bannerImageDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BannerImageDescriptionUpdateComponent,
    resolve: {
      bannerImageDescription: BannerImageDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.bannerImageDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BannerImageDescriptionUpdateComponent,
    resolve: {
      bannerImageDescription: BannerImageDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.bannerImageDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const bannerImageDescriptionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BannerImageDescriptionDeletePopupComponent,
    resolve: {
      bannerImageDescription: BannerImageDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.bannerImageDescription.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
