import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICustomerWishlist, CustomerWishlist } from 'app/shared/model/customer-wishlist.model';
import { CustomerWishlistService } from './customer-wishlist.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';

@Component({
  selector: 'jhi-customer-wishlist-update',
  templateUrl: './customer-wishlist-update.component.html'
})
export class CustomerWishlistUpdateComponent implements OnInit {
  isSaving: boolean;

  customers: ICustomer[];

  products: IProduct[];

  editForm = this.fb.group({
    id: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    customerIdId: [],
    productIdId: [],
    productId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected customerWishlistService: CustomerWishlistService,
    protected customerService: CustomerService,
    protected productService: ProductService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customerWishlist }) => {
      this.updateForm(customerWishlist);
    });
    this.customerService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICustomer[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICustomer[]>) => response.body)
      )
      .subscribe((res: ICustomer[]) => (this.customers = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(customerWishlist: ICustomerWishlist) {
    this.editForm.patchValue({
      id: customerWishlist.id,
      isActive: customerWishlist.isActive,
      createdBy: customerWishlist.createdBy,
      modifiedBy: customerWishlist.modifiedBy,
      createdDate: customerWishlist.createdDate,
      modifiedDate: customerWishlist.modifiedDate,
      customerIdId: customerWishlist.customerIdId,
      productIdId: customerWishlist.productIdId,
      productId: customerWishlist.productId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customerWishlist = this.createFromForm();
    if (customerWishlist.id !== undefined) {
      this.subscribeToSaveResponse(this.customerWishlistService.update(customerWishlist));
    } else {
      this.subscribeToSaveResponse(this.customerWishlistService.create(customerWishlist));
    }
  }

  private createFromForm(): ICustomerWishlist {
    return {
      ...new CustomerWishlist(),
      id: this.editForm.get(['id']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      customerIdId: this.editForm.get(['customerIdId']).value,
      productIdId: this.editForm.get(['productIdId']).value,
      productId: this.editForm.get(['productId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomerWishlist>>) {
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

  trackProductById(index: number, item: IProduct) {
    return item.id;
  }
}
