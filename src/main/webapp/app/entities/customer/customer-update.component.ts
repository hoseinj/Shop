import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICustomer, Customer } from 'app/shared/model/customer.model';
import { CustomerService } from './customer.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';
import { IZone } from 'app/shared/model/zone.model';
import { ZoneService } from 'app/entities/zone/zone.service';
import { ICustomerGroup } from 'app/shared/model/customer-group.model';
import { CustomerGroupService } from 'app/entities/customer-group/customer-group.service';

@Component({
  selector: 'jhi-customer-update',
  templateUrl: './customer-update.component.html'
})
export class CustomerUpdateComponent implements OnInit {
  isSaving: boolean;

  countries: ICountry[];

  zones: IZone[];

  customergroups: ICustomerGroup[];

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
    username: [],
    email: [],
    password: [],
    mobile: [],
    address: [],
    city: [],
    pincode: [],
    avatar: [],
    avatarPath: [],
    mailStatus: [],
    deleteFlag: [],
    lastlogin: [],
    newsletter: [],
    safe: [],
    ip: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    countryIdId: [],
    zoneIdId: [],
    customerGroupIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected customerService: CustomerService,
    protected countryService: CountryService,
    protected zoneService: ZoneService,
    protected customerGroupService: CustomerGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customer }) => {
      this.updateForm(customer);
    });
    this.countryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICountry[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICountry[]>) => response.body)
      )
      .subscribe((res: ICountry[]) => (this.countries = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.zoneService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IZone[]>) => mayBeOk.ok),
        map((response: HttpResponse<IZone[]>) => response.body)
      )
      .subscribe((res: IZone[]) => (this.zones = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.customerGroupService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICustomerGroup[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICustomerGroup[]>) => response.body)
      )
      .subscribe((res: ICustomerGroup[]) => (this.customergroups = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(customer: ICustomer) {
    this.editForm.patchValue({
      id: customer.id,
      firstName: customer.firstName,
      lastName: customer.lastName,
      username: customer.username,
      email: customer.email,
      password: customer.password,
      mobile: customer.mobile,
      address: customer.address,
      city: customer.city,
      pincode: customer.pincode,
      avatar: customer.avatar,
      avatarPath: customer.avatarPath,
      mailStatus: customer.mailStatus,
      deleteFlag: customer.deleteFlag,
      lastlogin: customer.lastlogin,
      newsletter: customer.newsletter,
      safe: customer.safe,
      ip: customer.ip,
      isActive: customer.isActive,
      createdBy: customer.createdBy,
      modifiedBy: customer.modifiedBy,
      createdDate: customer.createdDate,
      modifiedDate: customer.modifiedDate,
      countryIdId: customer.countryIdId,
      zoneIdId: customer.zoneIdId,
      customerGroupIdId: customer.customerGroupIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customer = this.createFromForm();
    if (customer.id !== undefined) {
      this.subscribeToSaveResponse(this.customerService.update(customer));
    } else {
      this.subscribeToSaveResponse(this.customerService.create(customer));
    }
  }

  private createFromForm(): ICustomer {
    return {
      ...new Customer(),
      id: this.editForm.get(['id']).value,
      firstName: this.editForm.get(['firstName']).value,
      lastName: this.editForm.get(['lastName']).value,
      username: this.editForm.get(['username']).value,
      email: this.editForm.get(['email']).value,
      password: this.editForm.get(['password']).value,
      mobile: this.editForm.get(['mobile']).value,
      address: this.editForm.get(['address']).value,
      city: this.editForm.get(['city']).value,
      pincode: this.editForm.get(['pincode']).value,
      avatar: this.editForm.get(['avatar']).value,
      avatarPath: this.editForm.get(['avatarPath']).value,
      mailStatus: this.editForm.get(['mailStatus']).value,
      deleteFlag: this.editForm.get(['deleteFlag']).value,
      lastlogin: this.editForm.get(['lastlogin']).value,
      newsletter: this.editForm.get(['newsletter']).value,
      safe: this.editForm.get(['safe']).value,
      ip: this.editForm.get(['ip']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      countryIdId: this.editForm.get(['countryIdId']).value,
      zoneIdId: this.editForm.get(['zoneIdId']).value,
      customerGroupIdId: this.editForm.get(['customerGroupIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomer>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackCountryById(index: number, item: ICountry) {
    return item.id;
  }

  trackZoneById(index: number, item: IZone) {
    return item.id;
  }

  trackCustomerGroupById(index: number, item: ICustomerGroup) {
    return item.id;
  }
}
