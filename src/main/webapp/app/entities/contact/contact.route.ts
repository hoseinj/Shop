import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Contact } from 'app/shared/model/contact.model';
import { ContactService } from './contact.service';
import { ContactComponent } from './contact.component';
import { ContactDetailComponent } from './contact-detail.component';
import { ContactUpdateComponent } from './contact-update.component';
import { ContactDeletePopupComponent } from './contact-delete-dialog.component';
import { IContact } from 'app/shared/model/contact.model';

@Injectable({ providedIn: 'root' })
export class ContactResolve implements Resolve<IContact> {
  constructor(private service: ContactService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IContact> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Contact>) => response.ok),
        map((contact: HttpResponse<Contact>) => contact.body)
      );
    }
    return of(new Contact());
  }
}

export const contactRoute: Routes = [
  {
    path: '',
    component: ContactComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.contact.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ContactDetailComponent,
    resolve: {
      contact: ContactResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.contact.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ContactUpdateComponent,
    resolve: {
      contact: ContactResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.contact.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ContactUpdateComponent,
    resolve: {
      contact: ContactResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.contact.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const contactPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ContactDeletePopupComponent,
    resolve: {
      contact: ContactResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.contact.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
