import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProductRelated } from 'app/shared/model/product-related.model';
import { ProductRelatedService } from './product-related.service';
import { ProductRelatedComponent } from './product-related.component';
import { ProductRelatedDetailComponent } from './product-related-detail.component';
import { ProductRelatedUpdateComponent } from './product-related-update.component';
import { ProductRelatedDeletePopupComponent } from './product-related-delete-dialog.component';
import { IProductRelated } from 'app/shared/model/product-related.model';

@Injectable({ providedIn: 'root' })
export class ProductRelatedResolve implements Resolve<IProductRelated> {
  constructor(private service: ProductRelatedService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IProductRelated> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ProductRelated>) => response.ok),
        map((productRelated: HttpResponse<ProductRelated>) => productRelated.body)
      );
    }
    return of(new ProductRelated());
  }
}

export const productRelatedRoute: Routes = [
  {
    path: '',
    component: ProductRelatedComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.productRelated.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductRelatedDetailComponent,
    resolve: {
      productRelated: ProductRelatedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productRelated.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductRelatedUpdateComponent,
    resolve: {
      productRelated: ProductRelatedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productRelated.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductRelatedUpdateComponent,
    resolve: {
      productRelated: ProductRelatedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productRelated.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const productRelatedPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProductRelatedDeletePopupComponent,
    resolve: {
      productRelated: ProductRelatedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productRelated.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
