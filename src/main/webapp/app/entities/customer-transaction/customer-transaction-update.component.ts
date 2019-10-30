import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICustomerTransaction, CustomerTransaction } from 'app/shared/model/customer-transaction.model';
import { CustomerTransactionService } from './customer-transaction.service';

@Component({
  selector: 'jhi-customer-transaction-update',
  templateUrl: './customer-transaction-update.component.html'
})
export class CustomerTransactionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    description: [],
    amount: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(
    protected customerTransactionService: CustomerTransactionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customerTransaction }) => {
      this.updateForm(customerTransaction);
    });
  }

  updateForm(customerTransaction: ICustomerTransaction) {
    this.editForm.patchValue({
      id: customerTransaction.id,
      description: customerTransaction.description,
      amount: customerTransaction.amount,
      isActive: customerTransaction.isActive,
      createdBy: customerTransaction.createdBy,
      modifiedBy: customerTransaction.modifiedBy,
      createdDate: customerTransaction.createdDate,
      modifiedDate: customerTransaction.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customerTransaction = this.createFromForm();
    if (customerTransaction.id !== undefined) {
      this.subscribeToSaveResponse(this.customerTransactionService.update(customerTransaction));
    } else {
      this.subscribeToSaveResponse(this.customerTransactionService.create(customerTransaction));
    }
  }

  private createFromForm(): ICustomerTransaction {
    return {
      ...new CustomerTransaction(),
      id: this.editForm.get(['id']).value,
      description: this.editForm.get(['description']).value,
      amount: this.editForm.get(['amount']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomerTransaction>>) {
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
