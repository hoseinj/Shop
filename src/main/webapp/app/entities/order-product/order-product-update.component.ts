import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IOrderProduct, OrderProduct } from 'app/shared/model/order-product.model';
import { OrderProductService } from './order-product.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IOrder } from 'app/shared/model/order.model';
import { OrderService } from 'app/entities/order/order.service';

@Component({
  selector: 'jhi-order-product-update',
  templateUrl: './order-product-update.component.html'
})
export class OrderProductUpdateComponent implements OnInit {
  isSaving: boolean;

  products: IProduct[];

  orders: IOrder[];

  editForm = this.fb.group({
    id: [],
    name: [],
    model: [],
    quantity: [],
    trace: [],
    total: [],
    tax: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    productIdId: [],
    orderIdId: [],
    productId: [],
    orderId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected orderProductService: OrderProductService,
    protected productService: ProductService,
    protected orderService: OrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orderProduct }) => {
      this.updateForm(orderProduct);
    });
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.orderService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrder[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrder[]>) => response.body)
      )
      .subscribe((res: IOrder[]) => (this.orders = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(orderProduct: IOrderProduct) {
    this.editForm.patchValue({
      id: orderProduct.id,
      name: orderProduct.name,
      model: orderProduct.model,
      quantity: orderProduct.quantity,
      trace: orderProduct.trace,
      total: orderProduct.total,
      tax: orderProduct.tax,
      isActive: orderProduct.isActive,
      createdBy: orderProduct.createdBy,
      modifiedBy: orderProduct.modifiedBy,
      createdDate: orderProduct.createdDate,
      modifiedDate: orderProduct.modifiedDate,
      productIdId: orderProduct.productIdId,
      orderIdId: orderProduct.orderIdId,
      productId: orderProduct.productId,
      orderId: orderProduct.orderId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orderProduct = this.createFromForm();
    if (orderProduct.id !== undefined) {
      this.subscribeToSaveResponse(this.orderProductService.update(orderProduct));
    } else {
      this.subscribeToSaveResponse(this.orderProductService.create(orderProduct));
    }
  }

  private createFromForm(): IOrderProduct {
    return {
      ...new OrderProduct(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      model: this.editForm.get(['model']).value,
      quantity: this.editForm.get(['quantity']).value,
      trace: this.editForm.get(['trace']).value,
      total: this.editForm.get(['total']).value,
      tax: this.editForm.get(['tax']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      productIdId: this.editForm.get(['productIdId']).value,
      orderIdId: this.editForm.get(['orderIdId']).value,
      productId: this.editForm.get(['productId']).value,
      orderId: this.editForm.get(['orderId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderProduct>>) {
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

  trackProductById(index: number, item: IProduct) {
    return item.id;
  }

  trackOrderById(index: number, item: IOrder) {
    return item.id;
  }
}
