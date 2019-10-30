import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICategoryPath, CategoryPath } from 'app/shared/model/category-path.model';
import { CategoryPathService } from './category-path.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category/category.service';

@Component({
  selector: 'jhi-category-path-update',
  templateUrl: './category-path-update.component.html'
})
export class CategoryPathUpdateComponent implements OnInit {
  isSaving: boolean;

  categories: ICategory[];

  editForm = this.fb.group({
    id: [],
    level: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    categoryIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected categoryPathService: CategoryPathService,
    protected categoryService: CategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ categoryPath }) => {
      this.updateForm(categoryPath);
    });
    this.categoryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICategory[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICategory[]>) => response.body)
      )
      .subscribe((res: ICategory[]) => (this.categories = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(categoryPath: ICategoryPath) {
    this.editForm.patchValue({
      id: categoryPath.id,
      level: categoryPath.level,
      isActive: categoryPath.isActive,
      createdBy: categoryPath.createdBy,
      modifiedBy: categoryPath.modifiedBy,
      createdDate: categoryPath.createdDate,
      modifiedDate: categoryPath.modifiedDate,
      categoryIdId: categoryPath.categoryIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const categoryPath = this.createFromForm();
    if (categoryPath.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryPathService.update(categoryPath));
    } else {
      this.subscribeToSaveResponse(this.categoryPathService.create(categoryPath));
    }
  }

  private createFromForm(): ICategoryPath {
    return {
      ...new CategoryPath(),
      id: this.editForm.get(['id']).value,
      level: this.editForm.get(['level']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      categoryIdId: this.editForm.get(['categoryIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategoryPath>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackCategoryById(index: number, item: ICategory) {
    return item.id;
  }
}
