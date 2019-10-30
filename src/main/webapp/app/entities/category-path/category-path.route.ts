import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CategoryPath } from 'app/shared/model/category-path.model';
import { CategoryPathService } from './category-path.service';
import { CategoryPathComponent } from './category-path.component';
import { CategoryPathDetailComponent } from './category-path-detail.component';
import { CategoryPathUpdateComponent } from './category-path-update.component';
import { CategoryPathDeletePopupComponent } from './category-path-delete-dialog.component';
import { ICategoryPath } from 'app/shared/model/category-path.model';

@Injectable({ providedIn: 'root' })
export class CategoryPathResolve implements Resolve<ICategoryPath> {
  constructor(private service: CategoryPathService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICategoryPath> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CategoryPath>) => response.ok),
        map((categoryPath: HttpResponse<CategoryPath>) => categoryPath.body)
      );
    }
    return of(new CategoryPath());
  }
}

export const categoryPathRoute: Routes = [
  {
    path: '',
    component: CategoryPathComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.categoryPath.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategoryPathDetailComponent,
    resolve: {
      categoryPath: CategoryPathResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.categoryPath.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategoryPathUpdateComponent,
    resolve: {
      categoryPath: CategoryPathResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.categoryPath.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategoryPathUpdateComponent,
    resolve: {
      categoryPath: CategoryPathResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.categoryPath.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const categoryPathPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CategoryPathDeletePopupComponent,
    resolve: {
      categoryPath: CategoryPathResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.categoryPath.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
