import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICategory, Category } from 'app/shared/model/category.model';
import { CategoryService } from './category.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';

@Component({
  selector: 'jhi-category-update',
  templateUrl: './category-update.component.html'
})
export class CategoryUpdateComponent implements OnInit {
  isSaving: boolean;

  products: IProduct[];

  editForm = this.fb.group({
    id: [],
    name: [],
    image: [],
    imagePath: [],
    parentInt: [],
    sortOrder: [],
    metaTagTitle: [],
    metaTagDescription: [],
    metaTagKeyword: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    productId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected categoryService: CategoryService,
    protected productService: ProductService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ category }) => {
      this.updateForm(category);
    });
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(category: ICategory) {
    this.editForm.patchValue({
      id: category.id,
      name: category.name,
      image: category.image,
      imagePath: category.imagePath,
      parentInt: category.parentInt,
      sortOrder: category.sortOrder,
      metaTagTitle: category.metaTagTitle,
      metaTagDescription: category.metaTagDescription,
      metaTagKeyword: category.metaTagKeyword,
      isActive: category.isActive,
      createdBy: category.createdBy,
      modifiedBy: category.modifiedBy,
      createdDate: category.createdDate,
      modifiedDate: category.modifiedDate,
      productId: category.productId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const category = this.createFromForm();
    if (category.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryService.update(category));
    } else {
      this.subscribeToSaveResponse(this.categoryService.create(category));
    }
  }

  private createFromForm(): ICategory {
    return {
      ...new Category(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      image: this.editForm.get(['image']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      parentInt: this.editForm.get(['parentInt']).value,
      sortOrder: this.editForm.get(['sortOrder']).value,
      metaTagTitle: this.editForm.get(['metaTagTitle']).value,
      metaTagDescription: this.editForm.get(['metaTagDescription']).value,
      metaTagKeyword: this.editForm.get(['metaTagKeyword']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      productId: this.editForm.get(['productId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategory>>) {
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

  trackProductById(index: number, item: IProduct) {
    return item.id;
  }
}
