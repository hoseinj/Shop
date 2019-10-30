import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IProductRelated, ProductRelated } from 'app/shared/model/product-related.model';
import { ProductRelatedService } from './product-related.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';

@Component({
  selector: 'jhi-product-related-update',
  templateUrl: './product-related-update.component.html'
})
export class ProductRelatedUpdateComponent implements OnInit {
  isSaving: boolean;

  products: IProduct[];

  editForm = this.fb.group({
    id: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    productId: [],
    productIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected productRelatedService: ProductRelatedService,
    protected productService: ProductService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productRelated }) => {
      this.updateForm(productRelated);
    });
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(productRelated: IProductRelated) {
    this.editForm.patchValue({
      id: productRelated.id,
      isActive: productRelated.isActive,
      createdBy: productRelated.createdBy,
      modifiedBy: productRelated.modifiedBy,
      createdDate: productRelated.createdDate,
      modifiedDate: productRelated.modifiedDate,
      productId: productRelated.productId,
      productIdId: productRelated.productIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const productRelated = this.createFromForm();
    if (productRelated.id !== undefined) {
      this.subscribeToSaveResponse(this.productRelatedService.update(productRelated));
    } else {
      this.subscribeToSaveResponse(this.productRelatedService.create(productRelated));
    }
  }

  private createFromForm(): IProductRelated {
    return {
      ...new ProductRelated(),
      id: this.editForm.get(['id']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      productId: this.editForm.get(['productId']).value,
      productIdId: this.editForm.get(['productIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductRelated>>) {
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
