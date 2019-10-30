import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMigrations, Migrations } from 'app/shared/model/migrations.model';
import { MigrationsService } from './migrations.service';

@Component({
  selector: 'jhi-migrations-update',
  templateUrl: './migrations-update.component.html'
})
export class MigrationsUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    timestamp: [],
    name: [],
    invoiceNo: [],
    invoicePrefix: [],
    firstname: [],
    lastname: [],
    email: [],
    telephone: [],
    fax: [],
    shippingFirstname: [],
    shippingLastname: [],
    shippingCompany: [],
    shippingAddress1: [],
    shippingAddress2: [],
    shippingCity: [],
    shippingPostcode: [],
    shippingCountry: [],
    shippingZone: [],
    shippingAddressFormat: [],
    shippingMethod: [],
    paymentFirstname: [],
    paymentLastname: [],
    paymentCompany: [],
    paymentAddress1: [],
    paymentAddress2: [],
    paymentCity: [],
    paymentPostcode: [],
    paymentCountry: [],
    paymentZone: [],
    paymentAddressFormat: [],
    paymentMethod: [],
    comment: [],
    total: [],
    reward: [],
    commision: [],
    currencyCode: [],
    currencyValue: [],
    ip: [],
    paymentFlag: [],
    orderName: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected migrationsService: MigrationsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ migrations }) => {
      this.updateForm(migrations);
    });
  }

  updateForm(migrations: IMigrations) {
    this.editForm.patchValue({
      id: migrations.id,
      timestamp: migrations.timestamp,
      name: migrations.name,
      invoiceNo: migrations.invoiceNo,
      invoicePrefix: migrations.invoicePrefix,
      firstname: migrations.firstname,
      lastname: migrations.lastname,
      email: migrations.email,
      telephone: migrations.telephone,
      fax: migrations.fax,
      shippingFirstname: migrations.shippingFirstname,
      shippingLastname: migrations.shippingLastname,
      shippingCompany: migrations.shippingCompany,
      shippingAddress1: migrations.shippingAddress1,
      shippingAddress2: migrations.shippingAddress2,
      shippingCity: migrations.shippingCity,
      shippingPostcode: migrations.shippingPostcode,
      shippingCountry: migrations.shippingCountry,
      shippingZone: migrations.shippingZone,
      shippingAddressFormat: migrations.shippingAddressFormat,
      shippingMethod: migrations.shippingMethod,
      paymentFirstname: migrations.paymentFirstname,
      paymentLastname: migrations.paymentLastname,
      paymentCompany: migrations.paymentCompany,
      paymentAddress1: migrations.paymentAddress1,
      paymentAddress2: migrations.paymentAddress2,
      paymentCity: migrations.paymentCity,
      paymentPostcode: migrations.paymentPostcode,
      paymentCountry: migrations.paymentCountry,
      paymentZone: migrations.paymentZone,
      paymentAddressFormat: migrations.paymentAddressFormat,
      paymentMethod: migrations.paymentMethod,
      comment: migrations.comment,
      total: migrations.total,
      reward: migrations.reward,
      commision: migrations.commision,
      currencyCode: migrations.currencyCode,
      currencyValue: migrations.currencyValue,
      ip: migrations.ip,
      paymentFlag: migrations.paymentFlag,
      orderName: migrations.orderName,
      isActive: migrations.isActive,
      createdBy: migrations.createdBy,
      modifiedBy: migrations.modifiedBy,
      createdDate: migrations.createdDate,
      modifiedDate: migrations.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const migrations = this.createFromForm();
    if (migrations.id !== undefined) {
      this.subscribeToSaveResponse(this.migrationsService.update(migrations));
    } else {
      this.subscribeToSaveResponse(this.migrationsService.create(migrations));
    }
  }

  private createFromForm(): IMigrations {
    return {
      ...new Migrations(),
      id: this.editForm.get(['id']).value,
      timestamp: this.editForm.get(['timestamp']).value,
      name: this.editForm.get(['name']).value,
      invoiceNo: this.editForm.get(['invoiceNo']).value,
      invoicePrefix: this.editForm.get(['invoicePrefix']).value,
      firstname: this.editForm.get(['firstname']).value,
      lastname: this.editForm.get(['lastname']).value,
      email: this.editForm.get(['email']).value,
      telephone: this.editForm.get(['telephone']).value,
      fax: this.editForm.get(['fax']).value,
      shippingFirstname: this.editForm.get(['shippingFirstname']).value,
      shippingLastname: this.editForm.get(['shippingLastname']).value,
      shippingCompany: this.editForm.get(['shippingCompany']).value,
      shippingAddress1: this.editForm.get(['shippingAddress1']).value,
      shippingAddress2: this.editForm.get(['shippingAddress2']).value,
      shippingCity: this.editForm.get(['shippingCity']).value,
      shippingPostcode: this.editForm.get(['shippingPostcode']).value,
      shippingCountry: this.editForm.get(['shippingCountry']).value,
      shippingZone: this.editForm.get(['shippingZone']).value,
      shippingAddressFormat: this.editForm.get(['shippingAddressFormat']).value,
      shippingMethod: this.editForm.get(['shippingMethod']).value,
      paymentFirstname: this.editForm.get(['paymentFirstname']).value,
      paymentLastname: this.editForm.get(['paymentLastname']).value,
      paymentCompany: this.editForm.get(['paymentCompany']).value,
      paymentAddress1: this.editForm.get(['paymentAddress1']).value,
      paymentAddress2: this.editForm.get(['paymentAddress2']).value,
      paymentCity: this.editForm.get(['paymentCity']).value,
      paymentPostcode: this.editForm.get(['paymentPostcode']).value,
      paymentCountry: this.editForm.get(['paymentCountry']).value,
      paymentZone: this.editForm.get(['paymentZone']).value,
      paymentAddressFormat: this.editForm.get(['paymentAddressFormat']).value,
      paymentMethod: this.editForm.get(['paymentMethod']).value,
      comment: this.editForm.get(['comment']).value,
      total: this.editForm.get(['total']).value,
      reward: this.editForm.get(['reward']).value,
      commision: this.editForm.get(['commision']).value,
      currencyCode: this.editForm.get(['currencyCode']).value,
      currencyValue: this.editForm.get(['currencyValue']).value,
      ip: this.editForm.get(['ip']).value,
      paymentFlag: this.editForm.get(['paymentFlag']).value,
      orderName: this.editForm.get(['orderName']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMigrations>>) {
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
