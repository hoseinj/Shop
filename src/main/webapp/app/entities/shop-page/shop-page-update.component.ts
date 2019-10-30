import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IShopPage, ShopPage } from 'app/shared/model/shop-page.model';
import { ShopPageService } from './shop-page.service';

@Component({
  selector: 'jhi-shop-page-update',
  templateUrl: './shop-page-update.component.html'
})
export class ShopPageUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    title: [],
    intro: [],
    fullText: [],
    sortOrder: [],
    metaTagTitle: [],
    metaTagDescroption: [],
    metaTagKeywords: [],
    viewPageCount: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected shopPageService: ShopPageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ shopPage }) => {
      this.updateForm(shopPage);
    });
  }

  updateForm(shopPage: IShopPage) {
    this.editForm.patchValue({
      id: shopPage.id,
      title: shopPage.title,
      intro: shopPage.intro,
      fullText: shopPage.fullText,
      sortOrder: shopPage.sortOrder,
      metaTagTitle: shopPage.metaTagTitle,
      metaTagDescroption: shopPage.metaTagDescroption,
      metaTagKeywords: shopPage.metaTagKeywords,
      viewPageCount: shopPage.viewPageCount,
      isActive: shopPage.isActive,
      createdBy: shopPage.createdBy,
      modifiedBy: shopPage.modifiedBy,
      createdDate: shopPage.createdDate,
      modifiedDate: shopPage.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const shopPage = this.createFromForm();
    if (shopPage.id !== undefined) {
      this.subscribeToSaveResponse(this.shopPageService.update(shopPage));
    } else {
      this.subscribeToSaveResponse(this.shopPageService.create(shopPage));
    }
  }

  private createFromForm(): IShopPage {
    return {
      ...new ShopPage(),
      id: this.editForm.get(['id']).value,
      title: this.editForm.get(['title']).value,
      intro: this.editForm.get(['intro']).value,
      fullText: this.editForm.get(['fullText']).value,
      sortOrder: this.editForm.get(['sortOrder']).value,
      metaTagTitle: this.editForm.get(['metaTagTitle']).value,
      metaTagDescroption: this.editForm.get(['metaTagDescroption']).value,
      metaTagKeywords: this.editForm.get(['metaTagKeywords']).value,
      viewPageCount: this.editForm.get(['viewPageCount']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShopPage>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
