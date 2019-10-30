import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PageGroup } from 'app/shared/model/page-group.model';
import { PageGroupService } from './page-group.service';
import { PageGroupComponent } from './page-group.component';
import { PageGroupDetailComponent } from './page-group-detail.component';
import { PageGroupUpdateComponent } from './page-group-update.component';
import { PageGroupDeletePopupComponent } from './page-group-delete-dialog.component';
import { IPageGroup } from 'app/shared/model/page-group.model';

@Injectable({ providedIn: 'root' })
export class PageGroupResolve implements Resolve<IPageGroup> {
  constructor(private service: PageGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPageGroup> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PageGroup>) => response.ok),
        map((pageGroup: HttpResponse<PageGroup>) => pageGroup.body)
      );
    }
    return of(new PageGroup());
  }
}

export const pageGroupRoute: Routes = [
  {
    path: '',
    component: PageGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.pageGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PageGroupDetailComponent,
    resolve: {
      pageGroup: PageGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.pageGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PageGroupUpdateComponent,
    resolve: {
      pageGroup: PageGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.pageGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PageGroupUpdateComponent,
    resolve: {
      pageGroup: PageGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.pageGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const pageGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PageGroupDeletePopupComponent,
    resolve: {
      pageGroup: PageGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.pageGroup.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
