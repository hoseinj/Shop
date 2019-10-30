import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CustomerWishlist } from 'app/shared/model/customer-wishlist.model';
import { CustomerWishlistService } from './customer-wishlist.service';
import { CustomerWishlistComponent } from './customer-wishlist.component';
import { CustomerWishlistDetailComponent } from './customer-wishlist-detail.component';
import { CustomerWishlistUpdateComponent } from './customer-wishlist-update.component';
import { CustomerWishlistDeletePopupComponent } from './customer-wishlist-delete-dialog.component';
import { ICustomerWishlist } from 'app/shared/model/customer-wishlist.model';

@Injectable({ providedIn: 'root' })
export class CustomerWishlistResolve implements Resolve<ICustomerWishlist> {
  constructor(private service: CustomerWishlistService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICustomerWishlist> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CustomerWishlist>) => response.ok),
        map((customerWishlist: HttpResponse<CustomerWishlist>) => customerWishlist.body)
      );
    }
    return of(new CustomerWishlist());
  }
}

export const customerWishlistRoute: Routes = [
  {
    path: '',
    component: CustomerWishlistComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.customerWishlist.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustomerWishlistDetailComponent,
    resolve: {
      customerWishlist: CustomerWishlistResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerWishlist.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustomerWishlistUpdateComponent,
    resolve: {
      customerWishlist: CustomerWishlistResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerWishlist.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustomerWishlistUpdateComponent,
    resolve: {
      customerWishlist: CustomerWishlistResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerWishlist.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const customerWishlistPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CustomerWishlistDeletePopupComponent,
    resolve: {
      customerWishlist: CustomerWishlistResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.customerWishlist.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
