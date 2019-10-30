import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IOrder, Order } from 'app/shared/model/order.model';
import { OrderService } from './order.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';
import { ICurrency } from 'app/shared/model/currency.model';
import { CurrencyService } from 'app/entities/currency/currency.service';
import { IOrderStatus } from 'app/shared/model/order-status.model';
import { OrderStatusService } from 'app/entities/order-status/order-status.service';

@Component({
  selector: 'jhi-order-update',
  templateUrl: './order-update.component.html'
})
export class OrderUpdateComponent implements OnInit {
  isSaving: boolean;

  customers: ICustomer[];

  currencies: ICurrency[];

  orderstatuses: IOrderStatus[];

  editForm = this.fb.group({
    id: [],
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
    customerIdId: [],
    currencyIdId: [],
    orderStatusIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected orderService: OrderService,
    protected customerService: CustomerService,
    protected currencyService: CurrencyService,
    protected orderStatusService: OrderStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ order }) => {
      this.updateForm(order);
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

  updateForm(order: IOrder) {
    this.editForm.patchValue({
      id: order.id,
      invoiceNo: order.invoiceNo,
      invoicePrefix: order.invoicePrefix,
      firstname: order.firstname,
      lastname: order.lastname,
      email: order.email,
      telephone: order.telephone,
      fax: order.fax,
      shippingFirstname: order.shippingFirstname,
      shippingLastname: order.shippingLastname,
      shippingCompany: order.shippingCompany,
      shippingAddress1: order.shippingAddress1,
      shippingAddress2: order.shippingAddress2,
      shippingCity: order.shippingCity,
      shippingPostcode: order.shippingPostcode,
      shippingCountry: order.shippingCountry,
      shippingZone: order.shippingZone,
      shippingAddressFormat: order.shippingAddressFormat,
      shippingMethod: order.shippingMethod,
      paymentFirstname: order.paymentFirstname,
      paymentLastname: order.paymentLastname,
      paymentCompany: order.paymentCompany,
      paymentAddress1: order.paymentAddress1,
      paymentAddress2: order.paymentAddress2,
      paymentCity: order.paymentCity,
      paymentPostcode: order.paymentPostcode,
      paymentCountry: order.paymentCountry,
      paymentZone: order.paymentZone,
      paymentAddressFormat: order.paymentAddressFormat,
      paymentMethod: order.paymentMethod,
      comment: order.comment,
      total: order.total,
      reward: order.reward,
      commision: order.commision,
      currencyCode: order.currencyCode,
      currencyValue: order.currencyValue,
      ip: order.ip,
      paymentFlag: order.paymentFlag,
      orderName: order.orderName,
      customerIdId: order.customerIdId,
      currencyIdId: order.currencyIdId,
      orderStatusIdId: order.orderStatusIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const order = this.createFromForm();
    if (order.id !== undefined) {
      this.subscribeToSaveResponse(this.orderService.update(order));
    } else {
      this.subscribeToSaveResponse(this.orderService.create(order));
    }
  }

  private createFromForm(): IOrder {
    return {
      ...new Order(),
      id: this.editForm.get(['id']).value,
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
      customerIdId: this.editForm.get(['customerIdId']).value,
      currencyIdId: this.editForm.get(['currencyIdId']).value,
      orderStatusIdId: this.editForm.get(['orderStatusIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrder>>) {
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
