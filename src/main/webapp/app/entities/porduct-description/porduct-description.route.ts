import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PorductDescription } from 'app/shared/model/porduct-description.model';
import { PorductDescriptionService } from './porduct-description.service';
import { PorductDescriptionComponent } from './porduct-description.component';
import { PorductDescriptionDetailComponent } from './porduct-description-detail.component';
import { PorductDescriptionUpdateComponent } from './porduct-description-update.component';
import { PorductDescriptionDeletePopupComponent } from './porduct-description-delete-dialog.component';
import { IPorductDescription } from 'app/shared/model/porduct-description.model';

@Injectable({ providedIn: 'root' })
export class PorductDescriptionResolve implements Resolve<IPorductDescription> {
  constructor(private service: PorductDescriptionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPorductDescription> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PorductDescription>) => response.ok),
        map((porductDescription: HttpResponse<PorductDescription>) => porductDescription.body)
      );
    }
    return of(new PorductDescription());
  }
}

export const porductDescriptionRoute: Routes = [
  {
    path: '',
    component: PorductDescriptionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.porductDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PorductDescriptionDetailComponent,
    resolve: {
      porductDescription: PorductDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.porductDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PorductDescriptionUpdateComponent,
    resolve: {
      porductDescription: PorductDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.porductDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PorductDescriptionUpdateComponent,
    resolve: {
      porductDescription: PorductDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.porductDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const porductDescriptionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PorductDescriptionDeletePopupComponent,
    resolve: {
      porductDescription: PorductDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.porductDescription.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
