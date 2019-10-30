import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProductDiscount, ProductDiscount } from 'app/shared/model/product-discount.model';
import { ProductDiscountService } from './product-discount.service';

@Component({
  selector: 'jhi-product-discount-update',
  templateUrl: './product-discount-update.component.html'
})
export class ProductDiscountUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    quantity: [],
    priority: [],
    pirce: [],
    dateStart: [],
    dateEnd: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(
    protected productDiscountService: ProductDiscountService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productDiscount }) => {
      this.updateForm(productDiscount);
    });
  }

  updateForm(productDiscount: IProductDiscount) {
    this.editForm.patchValue({
      id: productDiscount.id,
      quantity: productDiscount.quantity,
      priority: productDiscount.priority,
      pirce: productDiscount.pirce,
      dateStart: productDiscount.dateStart,
      dateEnd: productDiscount.dateEnd,
      isActive: productDiscount.isActive,
      createdBy: productDiscount.createdBy,
      modifiedBy: productDiscount.modifiedBy,
      createdDate: productDiscount.createdDate,
      modifiedDate: productDiscount.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const productDiscount = this.createFromForm();
    if (productDiscount.id !== undefined) {
      this.subscribeToSaveResponse(this.productDiscountService.update(productDiscount));
    } else {
      this.subscribeToSaveResponse(this.productDiscountService.create(productDiscount));
    }
  }

  private createFromForm(): IProductDiscount {
    return {
      ...new ProductDiscount(),
      id: this.editForm.get(['id']).value,
      quantity: this.editForm.get(['quantity']).value,
      priority: this.editForm.get(['priority']).value,
      pirce: this.editForm.get(['pirce']).value,
      dateStart: this.editForm.get(['dateStart']).value,
      dateEnd: this.editForm.get(['dateEnd']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductDiscount>>) {
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
