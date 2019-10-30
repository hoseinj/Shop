import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IProductRating, ProductRating } from 'app/shared/model/product-rating.model';
import { ProductRatingService } from './product-rating.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

@Component({
  selector: 'jhi-product-rating-update',
  templateUrl: './product-rating-update.component.html'
})
export class ProductRatingUpdateComponent implements OnInit {
  isSaving: boolean;

  products: IProduct[];

  customers: ICustomer[];

  editForm = this.fb.group({
    id: [],
    firstname: [],
    lastname: [],
    email: [],
    rating: [],
    review: [],
    imagePath: [],
    image: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    productId: [],
    productIdId: [],
    customerIdId: [],
    customerId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected productRatingService: ProductRatingService,
    protected productService: ProductService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productRating }) => {
      this.updateForm(productRating);
    });
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.customerService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICustomer[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICustomer[]>) => response.body)
      )
      .subscribe((res: ICustomer[]) => (this.customers = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(productRating: IProductRating) {
    this.editForm.patchValue({
      id: productRating.id,
      firstname: productRating.firstname,
      lastname: productRating.lastname,
      email: productRating.email,
      rating: productRating.rating,
      review: productRating.review,
      imagePath: productRating.imagePath,
      image: productRating.image,
      isActive: productRating.isActive,
      createdBy: productRating.createdBy,
      modifiedBy: productRating.modifiedBy,
      createdDate: productRating.createdDate,
      modifiedDate: productRating.modifiedDate,
      productId: productRating.productId,
      productIdId: productRating.productIdId,
      customerIdId: productRating.customerIdId,
      customerId: productRating.customerId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const productRating = this.createFromForm();
    if (productRating.id !== undefined) {
      this.subscribeToSaveResponse(this.productRatingService.update(productRating));
    } else {
      this.subscribeToSaveResponse(this.productRatingService.create(productRating));
    }
  }

  private createFromForm(): IProductRating {
    return {
      ...new ProductRating(),
      id: this.editForm.get(['id']).value,
      firstname: this.editForm.get(['firstname']).value,
      lastname: this.editForm.get(['lastname']).value,
      email: this.editForm.get(['email']).value,
      rating: this.editForm.get(['rating']).value,
      review: this.editForm.get(['review']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      image: this.editForm.get(['image']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      productId: this.editForm.get(['productId']).value,
      productIdId: this.editForm.get(['productIdId']).value,
      customerIdId: this.editForm.get(['customerIdId']).value,
      customerId: this.editForm.get(['customerId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductRating>>) {
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

  trackCustomerById(index: number, item: ICustomer) {
    return item.id;
  }
}
