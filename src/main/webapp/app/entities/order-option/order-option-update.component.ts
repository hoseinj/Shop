import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IOrderOption, OrderOption } from 'app/shared/model/order-option.model';
import { OrderOptionService } from './order-option.service';

@Component({
  selector: 'jhi-order-option-update',
  templateUrl: './order-option-update.component.html'
})
export class OrderOptionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    value: [],
    type: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected orderOptionService: OrderOptionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orderOption }) => {
      this.updateForm(orderOption);
    });
  }

  updateForm(orderOption: IOrderOption) {
    this.editForm.patchValue({
      id: orderOption.id,
      name: orderOption.name,
      value: orderOption.value,
      type: orderOption.type,
      isActive: orderOption.isActive,
      createdBy: orderOption.createdBy,
      modifiedBy: orderOption.modifiedBy,
      createdDate: orderOption.createdDate,
      modifiedDate: orderOption.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orderOption = this.createFromForm();
    if (orderOption.id !== undefined) {
      this.subscribeToSaveResponse(this.orderOptionService.update(orderOption));
    } else {
      this.subscribeToSaveResponse(this.orderOptionService.create(orderOption));
    }
  }

  private createFromForm(): IOrderOption {
    return {
      ...new OrderOption(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      value: this.editForm.get(['value']).value,
      type: this.editForm.get(['type']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderOption>>) {
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
