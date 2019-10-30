import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAccessToken, AccessToken } from 'app/shared/model/access-token.model';
import { AccessTokenService } from './access-token.service';
import { IShopUser } from 'app/shared/model/shop-user.model';
import { ShopUserService } from 'app/entities/shop-user/shop-user.service';

@Component({
  selector: 'jhi-access-token-update',
  templateUrl: './access-token-update.component.html'
})
export class AccessTokenUpdateComponent implements OnInit {
  isSaving: boolean;

  shopusers: IShopUser[];

  editForm = this.fb.group({
    id: [],
    token: [],
    shopUserId: [],
    shopUserIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected accessTokenService: AccessTokenService,
    protected shopUserService: ShopUserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ accessToken }) => {
      this.updateForm(accessToken);
    });
    this.shopUserService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IShopUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IShopUser[]>) => response.body)
      )
      .subscribe((res: IShopUser[]) => (this.shopusers = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(accessToken: IAccessToken) {
    this.editForm.patchValue({
      id: accessToken.id,
      token: accessToken.token,
      shopUserId: accessToken.shopUserId,
      shopUserIdId: accessToken.shopUserIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const accessToken = this.createFromForm();
    if (accessToken.id !== undefined) {
      this.subscribeToSaveResponse(this.accessTokenService.update(accessToken));
    } else {
      this.subscribeToSaveResponse(this.accessTokenService.create(accessToken));
    }
  }

  private createFromForm(): IAccessToken {
    return {
      ...new AccessToken(),
      id: this.editForm.get(['id']).value,
      token: this.editForm.get(['token']).value,
      shopUserId: this.editForm.get(['shopUserId']).value,
      shopUserIdId: this.editForm.get(['shopUserIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAccessToken>>) {
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

  trackShopUserById(index: number, item: IShopUser) {
    return item.id;
  }
}
