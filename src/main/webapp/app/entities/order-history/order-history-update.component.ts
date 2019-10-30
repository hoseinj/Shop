import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IOrderHistory, OrderHistory } from 'app/shared/model/order-history.model';
import { OrderHistoryService } from './order-history.service';

@Component({
  selector: 'jhi-order-history-update',
  templateUrl: './order-history-update.component.html'
})
export class OrderHistoryUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    notify: [],
    comment: [],
    dateAdded: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected orderHistoryService: OrderHistoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orderHistory }) => {
      this.updateForm(orderHistory);
    });
  }

  updateForm(orderHistory: IOrderHistory) {
    this.editForm.patchValue({
      id: orderHistory.id,
      notify: orderHistory.notify,
      comment: orderHistory.comment,
      dateAdded: orderHistory.dateAdded,
      isActive: orderHistory.isActive,
      createdBy: orderHistory.createdBy,
      modifiedBy: orderHistory.modifiedBy,
      createdDate: orderHistory.createdDate,
      modifiedDate: orderHistory.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orderHistory = this.createFromForm();
    if (orderHistory.id !== undefined) {
      this.subscribeToSaveResponse(this.orderHistoryService.update(orderHistory));
    } else {
      this.subscribeToSaveResponse(this.orderHistoryService.create(orderHistory));
    }
  }

  private createFromForm(): IOrderHistory {
    return {
      ...new OrderHistory(),
      id: this.editForm.get(['id']).value,
      notify: this.editForm.get(['notify']).value,
      comment: this.editForm.get(['comment']).value,
      dateAdded: this.editForm.get(['dateAdded']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderHistory>>) {
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
