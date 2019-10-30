import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProductTag } from 'app/shared/model/product-tag.model';
import { ProductTagService } from './product-tag.service';
import { ProductTagComponent } from './product-tag.component';
import { ProductTagDetailComponent } from './product-tag-detail.component';
import { ProductTagUpdateComponent } from './product-tag-update.component';
import { ProductTagDeletePopupComponent } from './product-tag-delete-dialog.component';
import { IProductTag } from 'app/shared/model/product-tag.model';

@Injectable({ providedIn: 'root' })
export class ProductTagResolve implements Resolve<IProductTag> {
  constructor(private service: ProductTagService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IProductTag> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ProductTag>) => response.ok),
        map((productTag: HttpResponse<ProductTag>) => productTag.body)
      );
    }
    return of(new ProductTag());
  }
}

export const productTagRoute: Routes = [
  {
    path: '',
    component: ProductTagComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.productTag.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductTagDetailComponent,
    resolve: {
      productTag: ProductTagResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productTag.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductTagUpdateComponent,
    resolve: {
      productTag: ProductTagResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productTag.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductTagUpdateComponent,
    resolve: {
      productTag: ProductTagResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productTag.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const productTagPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProductTagDeletePopupComponent,
    resolve: {
      productTag: ProductTagResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.productTag.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
