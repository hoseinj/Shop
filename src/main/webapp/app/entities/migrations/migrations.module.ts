import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { MigrationsComponent } from './migrations.component';
import { MigrationsDetailComponent } from './migrations-detail.component';
import { MigrationsUpdateComponent } from './migrations-update.component';
import { MigrationsDeletePopupComponent, MigrationsDeleteDialogComponent } from './migrations-delete-dialog.component';
import { migrationsRoute, migrationsPopupRoute } from './migrations.route';

const ENTITY_STATES = [...migrationsRoute, ...migrationsPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MigrationsComponent,
    MigrationsDetailComponent,
    MigrationsUpdateComponent,
    MigrationsDeleteDialogComponent,
    MigrationsDeletePopupComponent
  ],
  entryComponents: [MigrationsDeleteDialogComponent]
})
export class ShopMigrationsModule {}
