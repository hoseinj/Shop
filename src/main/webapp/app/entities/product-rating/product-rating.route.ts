import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProductRating } from 'app/shared/model/product-rating.model';
import { ProductRatingService } from './product-rating.service';
import { ProductRatingComponent } from './product-rating.component';
import { ProductRatingDetailComponent } from './product-rating-detail.component';
import { ProductRatingUpdateComponent } from './product-rating-update.component';
import { ProductRatingDeletePopupComponent } from './product-rating-delete-dialog.component';
import { IProductRating } from 'app/shared/model/product-rating.model';

@Injectable({ providedIn: 'root' })
export class ProductRatingResolve implements Resolve<IProductRating> {
  constructor(private service: ProductRatingService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IProductRating> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ProductRating>) => response.ok),
        map((productRating: HttpResponse<ProductRating>) => productRating.body)
      );
    }
    return of(new ProductRating());
  }
}

export const productRatingRoute: Routes = [
  {
    path: '',
    component: ProductRatingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.productRating.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductRatingDetailComponent,
    resolve: {
      productRating: ProductRatingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productRating.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductRatingUpdateComponent,
    resolve: {
      productRating: ProductRatingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productRating.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductRatingUpdateComponent,
    resolve: {
      productRating: ProductRatingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productRating.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const productRatingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProductRatingDeletePopupComponent,
    resolve: {
      productRating: ProductRatingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productRating.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
