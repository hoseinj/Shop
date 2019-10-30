import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Migrations } from 'app/shared/model/migrations.model';
import { MigrationsService } from './migrations.service';
import { MigrationsComponent } from './migrations.component';
import { MigrationsDetailComponent } from './migrations-detail.component';
import { MigrationsUpdateComponent } from './migrations-update.component';
import { MigrationsDeletePopupComponent } from './migrations-delete-dialog.component';
import { IMigrations } from 'app/shared/model/migrations.model';

@Injectable({ providedIn: 'root' })
export class MigrationsResolve implements Resolve<IMigrations> {
  constructor(private service: MigrationsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMigrations> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Migrations>) => response.ok),
        map((migrations: HttpResponse<Migrations>) => migrations.body)
      );
    }
    return of(new Migrations());
  }
}

export const migrationsRoute: Routes = [
  {
    path: '',
    component: MigrationsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'shopApp.migrations.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MigrationsDetailComponent,
    resolve: {
      migrations: MigrationsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.migrations.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MigrationsUpdateComponent,
    resolve: {
      migrations: MigrationsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.migrations.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MigrationsUpdateComponent,
    resolve: {
      migrations: MigrationsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.migrations.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const migrationsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MigrationsDeletePopupComponent,
    resolve: {
      migrations: MigrationsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'shopApp.migrations.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
