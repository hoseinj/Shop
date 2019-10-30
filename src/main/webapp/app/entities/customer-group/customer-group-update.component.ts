import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICustomerGroup, CustomerGroup } from 'app/shared/model/customer-group.model';
import { CustomerGroupService } from './customer-group.service';

@Component({
  selector: 'jhi-customer-group-update',
  templateUrl: './customer-group-update.component.html'
})
export class CustomerGroupUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected customerGroupService: CustomerGroupService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customerGroup }) => {
      this.updateForm(customerGroup);
    });
  }

  updateForm(customerGroup: ICustomerGroup) {
    this.editForm.patchValue({
      id: customerGroup.id,
      name: customerGroup.name,
      description: customerGroup.description,
      isActive: customerGroup.isActive,
      createdBy: customerGroup.createdBy,
      modifiedBy: customerGroup.modifiedBy,
      createdDate: customerGroup.createdDate,
      modifiedDate: customerGroup.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customerGroup = this.createFromForm();
    if (customerGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.customerGroupService.update(customerGroup));
    } else {
      this.subscribeToSaveResponse(this.customerGroupService.create(customerGroup));
    }
  }

  private createFromForm(): ICustomerGroup {
    return {
      ...new CustomerGroup(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomerGroup>>) {
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
