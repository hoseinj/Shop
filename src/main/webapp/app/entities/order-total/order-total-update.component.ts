import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IOrderTotal, OrderTotal } from 'app/shared/model/order-total.model';
import { OrderTotalService } from './order-total.service';
import { IOrder } from 'app/shared/model/order.model';
import { OrderService } from 'app/entities/order/order.service';

@Component({
  selector: 'jhi-order-total-update',
  templateUrl: './order-total-update.component.html'
})
export class OrderTotalUpdateComponent implements OnInit {
  isSaving: boolean;

  orders: IOrder[];

  editForm = this.fb.group({
    id: [],
    value: [],
    orderIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected orderTotalService: OrderTotalService,
    protected orderService: OrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ orderTotal }) => {
      this.updateForm(orderTotal);
    });
    this.orderService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IOrder[]>) => mayBeOk.ok),
        map((response: HttpResponse<IOrder[]>) => response.body)
      )
      .subscribe((res: IOrder[]) => (this.orders = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(orderTotal: IOrderTotal) {
    this.editForm.patchValue({
      id: orderTotal.id,
      value: orderTotal.value,
      orderIdId: orderTotal.orderIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const orderTotal = this.createFromForm();
    if (orderTotal.id !== undefined) {
      this.subscribeToSaveResponse(this.orderTotalService.update(orderTotal));
    } else {
      this.subscribeToSaveResponse(this.orderTotalService.create(orderTotal));
    }
  }

  private createFromForm(): IOrderTotal {
    return {
      ...new OrderTotal(),
      id: this.editForm.get(['id']).value,
      value: this.editForm.get(['value']).value,
      orderIdId: this.editForm.get(['orderIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderTotal>>) {
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

  trackOrderById(index: number, item: IOrder) {
    return item.id;
  }
}
