import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IProductViewLog, ProductViewLog } from 'app/shared/model/product-view-log.model';
import { ProductViewLogService } from './product-view-log.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';

@Component({
  selector: 'jhi-product-view-log-update',
  templateUrl: './product-view-log-update.component.html'
})
export class ProductViewLogUpdateComponent implements OnInit {
  isSaving: boolean;

  products: IProduct[];

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
    email: [],
    userName: [],
    mobile: [],
    address: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    productIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected productViewLogService: ProductViewLogService,
    protected productService: ProductService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ productViewLog }) => {
      this.updateForm(productViewLog);
    });
    this.productService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduct[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduct[]>) => response.body)
      )
      .subscribe((res: IProduct[]) => (this.products = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(productViewLog: IProductViewLog) {
    this.editForm.patchValue({
      id: productViewLog.id,
      firstName: productViewLog.firstName,
      lastName: productViewLog.lastName,
      email: productViewLog.email,
      userName: productViewLog.userName,
      mobile: productViewLog.mobile,
      address: productViewLog.address,
      isActive: productViewLog.isActive,
      createdBy: productViewLog.createdBy,
      modifiedBy: productViewLog.modifiedBy,
      createdDate: productViewLog.createdDate,
      modifiedDate: productViewLog.modifiedDate,
      productIdId: productViewLog.productIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const productViewLog = this.createFromForm();
    if (productViewLog.id !== undefined) {
      this.subscribeToSaveResponse(this.productViewLogService.update(productViewLog));
    } else {
      this.subscribeToSaveResponse(this.productViewLogService.create(productViewLog));
    }
  }

  private createFromForm(): IProductViewLog {
    return {
      ...new ProductViewLog(),
      id: this.editForm.get(['id']).value,
      firstName: this.editForm.get(['firstName']).value,
      lastName: this.editForm.get(['lastName']).value,
      email: this.editForm.get(['email']).value,
      userName: this.editForm.get(['userName']).value,
      mobile: this.editForm.get(['mobile']).value,
      address: this.editForm.get(['address']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      productIdId: this.editForm.get(['productIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductViewLog>>) {
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
