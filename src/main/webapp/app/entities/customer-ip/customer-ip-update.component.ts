import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICustomerIp, CustomerIp } from 'app/shared/model/customer-ip.model';
import { CustomerIpService } from './customer-ip.service';

@Component({
  selector: 'jhi-customer-ip-update',
  templateUrl: './customer-ip-update.component.html'
})
export class CustomerIpUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ip: [],
    dateAdded: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected customerIpService: CustomerIpService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customerIp }) => {
      this.updateForm(customerIp);
    });
  }

  updateForm(customerIp: ICustomerIp) {
    this.editForm.patchValue({
      id: customerIp.id,
      ip: customerIp.ip,
      dateAdded: customerIp.dateAdded,
      isActive: customerIp.isActive,
      createdBy: customerIp.createdBy,
      modifiedBy: customerIp.modifiedBy,
      createdDate: customerIp.createdDate,
      modifiedDate: customerIp.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customerIp = this.createFromForm();
    if (customerIp.id !== undefined) {
      this.subscribeToSaveResponse(this.customerIpService.update(customerIp));
    } else {
      this.subscribeToSaveResponse(this.customerIpService.create(customerIp));
    }
  }

  private createFromForm(): ICustomerIp {
    return {
      ...new CustomerIp(),
      id: this.editForm.get(['id']).value,
      ip: this.editForm.get(['ip']).value,
      dateAdded: this.editForm.get(['dateAdded']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomerIp>>) {
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
