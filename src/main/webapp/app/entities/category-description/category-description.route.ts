import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CategoryDescription } from 'app/shared/model/category-description.model';
import { CategoryDescriptionService } from './category-description.service';
import { CategoryDescriptionComponent } from './category-description.component';
import { CategoryDescriptionDetailComponent } from './category-description-detail.component';
import { CategoryDescriptionUpdateComponent } from './category-description-update.component';
import { CategoryDescriptionDeletePopupComponent } from './category-description-delete-dialog.component';
import { ICategoryDescription } from 'app/shared/model/category-description.model';

@Injectable({ providedIn: 'root' })
export class CategoryDescriptionResolve implements Resolve<ICategoryDescription> {
  constructor(private service: CategoryDescriptionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICategoryDescription> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CategoryDescription>) => response.ok),
        map((categoryDescription: HttpResponse<CategoryDescription>) => categoryDescription.body)
      );
    }
    return of(new CategoryDescription());
  }
}

export const categoryDescriptionRoute: Routes = [
  {
    path: '',
    component: CategoryDescriptionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.categoryDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CategoryDescriptionDetailComponent,
    resolve: {
      categoryDescription: CategoryDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.categoryDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CategoryDescriptionUpdateComponent,
    resolve: {
      categoryDescription: CategoryDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.categoryDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CategoryDescriptionUpdateComponent,
    resolve: {
      categoryDescription: CategoryDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.categoryDescription.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const categoryDescriptionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CategoryDescriptionDeletePopupComponent,
    resolve: {
      categoryDescription: CategoryDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.categoryDescription.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
