import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IOrderStatus, OrderStatus } from 'app/shared/model/order-status.model';
import { OrderStatusService } from './order-status.service';

@Component({
  selector: 'jhi-order-status-update',
  templateUrl: './order-status-update.component.html'
})
export class OrderStatusUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    colorCode: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected orderStatusService: OrderStatusService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orderStatus }) => {
      this.updateForm(orderStatus);
    });
  }

  updateForm(orderStatus: IOrderStatus) {
    this.editForm.patchValue({
      id: orderStatus.id,
      name: orderStatus.name,
      colorCode: orderStatus.colorCode,
      isActive: orderStatus.isActive,
      createdBy: orderStatus.createdBy,
      modifiedBy: orderStatus.modifiedBy,
      createdDate: orderStatus.createdDate,
      modifiedDate: orderStatus.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orderStatus = this.createFromForm();
    if (orderStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.orderStatusService.update(orderStatus));
    } else {
      this.subscribeToSaveResponse(this.orderStatusService.create(orderStatus));
    }
  }

  private createFromForm(): IOrderStatus {
    return {
      ...new OrderStatus(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      colorCode: this.editForm.get(['colorCode']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderStatus>>) {
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
