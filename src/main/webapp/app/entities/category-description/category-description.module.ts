import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ShopSharedModule } from 'app/shared/shared.module';
import { CategoryDescriptionComponent } from './category-description.component';
import { CategoryDescriptionDetailComponent } from './category-description-detail.component';
import { CategoryDescriptionUpdateComponent } from './category-description-update.component';
import {
  CategoryDescriptionDeletePopupComponent,
  CategoryDescriptionDeleteDialogComponent
} from './category-description-delete-dialog.component';
import { categoryDescriptionRoute, categoryDescriptionPopupRoute } from './category-description.route';

const ENTITY_STATES = [...categoryDescriptionRoute, ...categoryDescriptionPopupRoute];

@NgModule({
  imports: [ShopSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CategoryDescriptionComponent,
    CategoryDescriptionDetailComponent,
    CategoryDescriptionUpdateComponent,
    CategoryDescriptionDeleteDialogComponent,
    CategoryDescriptionDeletePopupComponent
  ],
  entryComponents: [CategoryDescriptionDeleteDialogComponent]
})
export class ShopCategoryDescriptionModule {}
