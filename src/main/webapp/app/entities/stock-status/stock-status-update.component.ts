import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IStockStatus, StockStatus } from 'app/shared/model/stock-status.model';
import { StockStatusService } from './stock-status.service';

@Component({
  selector: 'jhi-stock-status-update',
  templateUrl: './stock-status-update.component.html'
})
export class StockStatusUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected stockStatusService: StockStatusService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ stockStatus }) => {
      this.updateForm(stockStatus);
    });
  }

  updateForm(stockStatus: IStockStatus) {
    this.editForm.patchValue({
      id: stockStatus.id,
      name: stockStatus.name,
      isActive: stockStatus.isActive,
      createdBy: stockStatus.createdBy,
      modifiedBy: stockStatus.modifiedBy,
      createdDate: stockStatus.createdDate,
      modifiedDate: stockStatus.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const stockStatus = this.createFromForm();
    if (stockStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.stockStatusService.update(stockStatus));
    } else {
      this.subscribeToSaveResponse(this.stockStatusService.create(stockStatus));
    }
  }

  private createFromForm(): IStockStatus {
    return {
      ...new StockStatus(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStockStatus>>) {
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
