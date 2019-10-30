import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GeoZone } from 'app/shared/model/geo-zone.model';
import { GeoZoneService } from './geo-zone.service';
import { GeoZoneComponent } from './geo-zone.component';
import { GeoZoneDetailComponent } from './geo-zone-detail.component';
import { GeoZoneUpdateComponent } from './geo-zone-update.component';
import { GeoZoneDeletePopupComponent } from './geo-zone-delete-dialog.component';
import { IGeoZone } from 'app/shared/model/geo-zone.model';

@Injectable({ providedIn: 'root' })
export class GeoZoneResolve implements Resolve<IGeoZone> {
  constructor(private service: GeoZoneService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGeoZone> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GeoZone>) => response.ok),
        map((geoZone: HttpResponse<GeoZone>) => geoZone.body)
      );
    }
    return of(new GeoZone());
  }
}

export const geoZoneRoute: Routes = [
  {
    path: '',
    component: GeoZoneComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.geoZone.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GeoZoneDetailComponent,
    resolve: {
      geoZone: GeoZoneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.geoZone.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GeoZoneUpdateComponent,
    resolve: {
      geoZone: GeoZoneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.geoZone.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GeoZoneUpdateComponent,
    resolve: {
      geoZone: GeoZoneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.geoZone.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const geoZonePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GeoZoneDeletePopupComponent,
    resolve: {
      geoZone: GeoZoneResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.geoZone.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
