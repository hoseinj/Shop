import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IOrderLog, OrderLog } from 'app/shared/model/order-log.model';
import { OrderLogService } from './order-log.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';
import { ICurrency } from 'app/shared/model/currency.model';
import { CurrencyService } from 'app/entities/currency/currency.service';
import { IOrderStatus } from 'app/shared/model/order-status.model';
import { OrderStatusService } from 'app/entities/order-status/order-status.service';

@Component({
  selector: 'jhi-order-log-update',
  templateUrl: './order-log-update.component.html'
})
export class OrderLogUpdateComponent implements OnInit {
  isSaving: boolean;

  customers: ICustomer[];

  currencies: ICurrency[];

  orderstatuses: IOrderStatus[];

  editForm = this.fb.group({
    id: [],
    invoiceNo: [],
    invocePerfix: [],
    firstname: [],
    lastname: [],
    email: [],
    telephone: [],
    fax: [],
    shippingFristname: [],
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
    modifiedDate: [],
    customerIdId: [],
    currencyIdId: [],
    orderStatusIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected orderLogService: OrderLogService,
    protected customerService: CustomerService,
    protected currencyService: CurrencyService,
    protected orderStatusService: OrderStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orderLog }) => {
      this.updateForm(orderLog);
    });
    this.customerService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICustomer[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICustomer[]>) => response.body)
      )
      .subscribe((res: ICustomer[]) => (this.customers = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.currencyService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICurrency[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICurrency[]>) => response.body)
      )
      .subscribe((res: ICurrency[]) => (this.currencies = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.orderStatusService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrderStatus[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrderStatus[]>) => response.body)
      )
      .subscribe((res: IOrderStatus[]) => (this.orderstatuses = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(orderLog: IOrderLog) {
    this.editForm.patchValue({
      id: orderLog.id,
      invoiceNo: orderLog.invoiceNo,
      invocePerfix: orderLog.invocePerfix,
      firstname: orderLog.firstname,
      lastname: orderLog.lastname,
      email: orderLog.email,
      telephone: orderLog.telephone,
      fax: orderLog.fax,
      shippingFristname: orderLog.shippingFristname,
      shippingLastname: orderLog.shippingLastname,
      shippingCompany: orderLog.shippingCompany,
      shippingAddress1: orderLog.shippingAddress1,
      shippingAddress2: orderLog.shippingAddress2,
      shippingCity: orderLog.shippingCity,
      shippingPostcode: orderLog.shippingPostcode,
      shippingCountry: orderLog.shippingCountry,
      shippingZone: orderLog.shippingZone,
      shippingAddressFormat: orderLog.shippingAddressFormat,
      shippingMethod: orderLog.shippingMethod,
      paymentFirstname: orderLog.paymentFirstname,
      paymentLastname: orderLog.paymentLastname,
      paymentCompany: orderLog.paymentCompany,
      paymentAddress1: orderLog.paymentAddress1,
      paymentAddress2: orderLog.paymentAddress2,
      paymentCity: orderLog.paymentCity,
      paymentPostcode: orderLog.paymentPostcode,
      paymentCountry: orderLog.paymentCountry,
      paymentZone: orderLog.paymentZone,
      paymentAddressFormat: orderLog.paymentAddressFormat,
      paymentMethod: orderLog.paymentMethod,
      comment: orderLog.comment,
      total: orderLog.total,
      reward: orderLog.reward,
      commision: orderLog.commision,
      currencyCode: orderLog.currencyCode,
      currencyValue: orderLog.currencyValue,
      ip: orderLog.ip,
      paymentFlag: orderLog.paymentFlag,
      orderName: orderLog.orderName,
      isActive: orderLog.isActive,
      createdBy: orderLog.createdBy,
      modifiedBy: orderLog.modifiedBy,
      createdDate: orderLog.createdDate,
      modifiedDate: orderLog.modifiedDate,
      customerIdId: orderLog.customerIdId,
      currencyIdId: orderLog.currencyIdId,
      orderStatusIdId: orderLog.orderStatusIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orderLog = this.createFromForm();
    if (orderLog.id !== undefined) {
      this.subscribeToSaveResponse(this.orderLogService.update(orderLog));
    } else {
      this.subscribeToSaveResponse(this.orderLogService.create(orderLog));
    }
  }

  private createFromForm(): IOrderLog {
    return {
      ...new OrderLog(),
      id: this.editForm.get(['id']).value,
      invoiceNo: this.editForm.get(['invoiceNo']).value,
      invocePerfix: this.editForm.get(['invocePerfix']).value,
      firstname: this.editForm.get(['firstname']).value,
      lastname: this.editForm.get(['lastname']).value,
      email: this.editForm.get(['email']).value,
      telephone: this.editForm.get(['telephone']).value,
      fax: this.editForm.get(['fax']).value,
      shippingFristname: this.editForm.get(['shippingFristname']).value,
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
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      customerIdId: this.editForm.get(['customerIdId']).value,
      currencyIdId: this.editForm.get(['currencyIdId']).value,
      orderStatusIdId: this.editForm.get(['orderStatusIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderLog>>) {
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

  trackCustomerById(index: number, item: ICustomer) {
    return item.id;
  }

  trackCurrencyById(index: number, item: ICurrency) {
    return item.id;
  }

  trackOrderStatusById(index: number, item: IOrderStatus) {
    return item.id;
  }
}
