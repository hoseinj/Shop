import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IProduct, Product } from 'app/shared/model/product.model';
import { ProductService } from './product.service';
import { IStockStatus } from 'app/shared/model/stock-status.model';
import { StockStatusService } from 'app/entities/stock-status/stock-status.service';
import { IManufacturer } from 'app/shared/model/manufacturer.model';
import { ManufacturerService } from 'app/entities/manufacturer/manufacturer.service';

@Component({
  selector: 'jhi-product-update',
  templateUrl: './product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {
  isSaving: boolean;

  stockstatuses: IStockStatus[];

  manufacturers: IManufacturer[];

  editForm = this.fb.group({
    id: [],
    sku: [],
    upc: [],
    quantity: [],
    image: [],
    imagePath: [],
    shipping: [],
    price: [],
    dateAvailable: [],
    sortOrder: [],
    name: [],
    description: [],
    amount: [],
    metaTagTitle: [],
    metaTagDescription: [],
    metaTagKeyword: [],
    discount: [],
    subtractStock: [],
    minimumQuantity: [],
    location: [],
    wishlistStatus: [],
    deletFlag: [],
    isFeatured: [],
    rating: [],
    condition: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    stockStatusidId: [],
    manufacturerIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected productService: ProductService,
    protected stockStatusService: StockStatusService,
    protected manufacturerService: ManufacturerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ product }) => {
      this.updateForm(product);
    });
    this.stockStatusService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IStockStatus[]>) => mayBeOk.ok),
        map((response: HttpResponse<IStockStatus[]>) => response.body)
      )
      .subscribe((res: IStockStatus[]) => (this.stockstatuses = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.manufacturerService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IManufacturer[]>) => mayBeOk.ok),
        map((response: HttpResponse<IManufacturer[]>) => response.body)
      )
      .subscribe((res: IManufacturer[]) => (this.manufacturers = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(product: IProduct) {
    this.editForm.patchValue({
      id: product.id,
      sku: product.sku,
      upc: product.upc,
      quantity: product.quantity,
      image: product.image,
      imagePath: product.imagePath,
      shipping: product.shipping,
      price: product.price,
      dateAvailable: product.dateAvailable,
      sortOrder: product.sortOrder,
      name: product.name,
      description: product.description,
      amount: product.amount,
      metaTagTitle: product.metaTagTitle,
      metaTagDescription: product.metaTagDescription,
      metaTagKeyword: product.metaTagKeyword,
      discount: product.discount,
      subtractStock: product.subtractStock,
      minimumQuantity: product.minimumQuantity,
      location: product.location,
      wishlistStatus: product.wishlistStatus,
      deletFlag: product.deletFlag,
      isFeatured: product.isFeatured,
      rating: product.rating,
      condition: product.condition,
      isActive: product.isActive,
      createdBy: product.createdBy,
      modifiedBy: product.modifiedBy,
      createdDate: product.createdDate,
      modifiedDate: product.modifiedDate,
      stockStatusidId: product.stockStatusidId,
      manufacturerIdId: product.manufacturerIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const product = this.createFromForm();
    if (product.id !== undefined) {
      this.subscribeToSaveResponse(this.productService.update(product));
    } else {
      this.subscribeToSaveResponse(this.productService.create(product));
    }
  }

  private createFromForm(): IProduct {
    return {
      ...new Product(),
      id: this.editForm.get(['id']).value,
      sku: this.editForm.get(['sku']).value,
      upc: this.editForm.get(['upc']).value,
      quantity: this.editForm.get(['quantity']).value,
      image: this.editForm.get(['image']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      shipping: this.editForm.get(['shipping']).value,
      price: this.editForm.get(['price']).value,
      dateAvailable: this.editForm.get(['dateAvailable']).value,
      sortOrder: this.editForm.get(['sortOrder']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      amount: this.editForm.get(['amount']).value,
      metaTagTitle: this.editForm.get(['metaTagTitle']).value,
      metaTagDescription: this.editForm.get(['metaTagDescription']).value,
      metaTagKeyword: this.editForm.get(['metaTagKeyword']).value,
      discount: this.editForm.get(['discount']).value,
      subtractStock: this.editForm.get(['subtractStock']).value,
      minimumQuantity: this.editForm.get(['minimumQuantity']).value,
      location: this.editForm.get(['location']).value,
      wishlistStatus: this.editForm.get(['wishlistStatus']).value,
      deletFlag: this.editForm.get(['deletFlag']).value,
      isFeatured: this.editForm.get(['isFeatured']).value,
      rating: this.editForm.get(['rating']).value,
      condition: this.editForm.get(['condition']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      stockStatusidId: this.editForm.get(['stockStatusidId']).value,
      manufacturerIdId: this.editForm.get(['manufacturerIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduct>>) {
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

  trackStockStatusById(index: number, item: IStockStatus) {
    return item.id;
  }

  trackManufacturerById(index: number, item: IManufacturer) {
    return item.id;
  }
}
