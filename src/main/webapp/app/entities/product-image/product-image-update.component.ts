import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IProductImage, ProductImage } from 'app/shared/model/product-image.model';
import { ProductImageService } from './product-image.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';

@Component({
  selector: 'jhi-product-image-update',
  templateUrl: './product-image-update.component.html'
})
export class ProductImageUpdateComponent implements OnInit {
  isSaving: boolean;

  products: IProduct[];

  editForm = this.fb.group({
    id: [],
    image: [],
    containerName: [],
    defualtImage: [],
    sortOrder: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    productId: [],
    productIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected productImageService: ProductImageService,
    protected productService: ProductService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productImage }) => {
      this.updateForm(productImage);
    });
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(productImage: IProductImage) {
    this.editForm.patchValue({
      id: productImage.id,
      image: productImage.image,
      containerName: productImage.containerName,
      defualtImage: productImage.defualtImage,
      sortOrder: productImage.sortOrder,
      isActive: productImage.isActive,
      createdBy: productImage.createdBy,
      modifiedBy: productImage.modifiedBy,
      createdDate: productImage.createdDate,
      modifiedDate: productImage.modifiedDate,
      productId: productImage.productId,
      productIdId: productImage.productIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const productImage = this.createFromForm();
    if (productImage.id !== undefined) {
      this.subscribeToSaveResponse(this.productImageService.update(productImage));
    } else {
      this.subscribeToSaveResponse(this.productImageService.create(productImage));
    }
  }

  private createFromForm(): IProductImage {
    return {
      ...new ProductImage(),
      id: this.editForm.get(['id']).value,
      image: this.editForm.get(['image']).value,
      containerName: this.editForm.get(['containerName']).value,
      defualtImage: this.editForm.get(['defualtImage']).value,
      sortOrder: this.editForm.get(['sortOrder']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      productId: this.editForm.get(['productId']).value,
      productIdId: this.editForm.get(['productIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductImage>>) {
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
}
