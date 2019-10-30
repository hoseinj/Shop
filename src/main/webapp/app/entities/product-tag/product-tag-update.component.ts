import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProductTag, ProductTag } from 'app/shared/model/product-tag.model';
import { ProductTagService } from './product-tag.service';

@Component({
  selector: 'jhi-product-tag-update',
  templateUrl: './product-tag-update.component.html'
})
export class ProductTagUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    productTagname: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected productTagService: ProductTagService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productTag }) => {
      this.updateForm(productTag);
    });
  }

  updateForm(productTag: IProductTag) {
    this.editForm.patchValue({
      id: productTag.id,
      productTagname: productTag.productTagname,
      isActive: productTag.isActive,
      createdBy: productTag.createdBy,
      modifiedBy: productTag.modifiedBy,
      createdDate: productTag.createdDate,
      modifiedDate: productTag.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const productTag = this.createFromForm();
    if (productTag.id !== undefined) {
      this.subscribeToSaveResponse(this.productTagService.update(productTag));
    } else {
      this.subscribeToSaveResponse(this.productTagService.create(productTag));
    }
  }

  private createFromForm(): IProductTag {
    return {
      ...new ProductTag(),
      id: this.editForm.get(['id']).value,
      productTagname: this.editForm.get(['productTagname']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductTag>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
