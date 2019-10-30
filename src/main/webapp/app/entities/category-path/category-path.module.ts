import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { CategoryPathComponent } from './category-path.component';
import { CategoryPathDetailComponent } from './category-path-detail.component';
import { CategoryPathUpdateComponent } from './category-path-update.component';
import { CategoryPathDeletePopupComponent, CategoryPathDeleteDialogComponent } from './category-path-delete-dialog.component';
import { categoryPathRoute, categoryPathPopupRoute } from './category-path.route';

const ENTITY_STATES = [...categoryPathRoute, ...categoryPathPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CategoryPathComponent,
    CategoryPathDetailComponent,
    CategoryPathUpdateComponent,
    CategoryPathDeleteDialogComponent,
    CategoryPathDeletePopupComponent
  ],
  entryComponents: [CategoryPathDeleteDialogComponent]
})
export class ShopCategoryPathModule {}
