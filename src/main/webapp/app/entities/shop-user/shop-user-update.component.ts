import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IShopUser, ShopUser } from 'app/shared/model/shop-user.model';
import { ShopUserService } from './shop-user.service';
import { IUserGroup } from 'app/shared/model/user-group.model';
import { UserGroupService } from 'app/entities/user-group/user-group.service';

@Component({
  selector: 'jhi-shop-user-update',
  templateUrl: './shop-user-update.component.html'
})
export class ShopUserUpdateComponent implements OnInit {
  isSaving: boolean;

  usergroups: IUserGroup[];

  editForm = this.fb.group({
    id: [],
    username: [],
    password: [],
    firstName: [],
    lastName: [],
    email: [],
    avatar: [],
    avatarPath: [],
    code: [],
    ip: [],
    address: [],
    phoneNumber: [],
    name: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    userGroupIdId: [],
    userGroupId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected shopUserService: ShopUserService,
    protected userGroupService: UserGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ shopUser }) => {
      this.updateForm(shopUser);
    });
    this.userGroupService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUserGroup[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUserGroup[]>) => response.body)
      )
      .subscribe((res: IUserGroup[]) => (this.usergroups = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(shopUser: IShopUser) {
    this.editForm.patchValue({
      id: shopUser.id,
      username: shopUser.username,
      password: shopUser.password,
      firstName: shopUser.firstName,
      lastName: shopUser.lastName,
      email: shopUser.email,
      avatar: shopUser.avatar,
      avatarPath: shopUser.avatarPath,
      code: shopUser.code,
      ip: shopUser.ip,
      address: shopUser.address,
      phoneNumber: shopUser.phoneNumber,
      name: shopUser.name,
      isActive: shopUser.isActive,
      createdBy: shopUser.createdBy,
      modifiedBy: shopUser.modifiedBy,
      createdDate: shopUser.createdDate,
      modifiedDate: shopUser.modifiedDate,
      userGroupIdId: shopUser.userGroupIdId,
      userGroupId: shopUser.userGroupId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const shopUser = this.createFromForm();
    if (shopUser.id !== undefined) {
      this.subscribeToSaveResponse(this.shopUserService.update(shopUser));
    } else {
      this.subscribeToSaveResponse(this.shopUserService.create(shopUser));
    }
  }

  private createFromForm(): IShopUser {
    return {
      ...new ShopUser(),
      id: this.editForm.get(['id']).value,
      username: this.editForm.get(['username']).value,
      password: this.editForm.get(['password']).value,
      firstName: this.editForm.get(['firstName']).value,
      lastName: this.editForm.get(['lastName']).value,
      email: this.editForm.get(['email']).value,
      avatar: this.editForm.get(['avatar']).value,
      avatarPath: this.editForm.get(['avatarPath']).value,
      code: this.editForm.get(['code']).value,
      ip: this.editForm.get(['ip']).value,
      address: this.editForm.get(['address']).value,
      phoneNumber: this.editForm.get(['phoneNumber']).value,
      name: this.editForm.get(['name']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      userGroupIdId: this.editForm.get(['userGroupIdId']).value,
      userGroupId: this.editForm.get(['userGroupId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShopUser>>) {
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

  trackUserGroupById(index: number, item: IUserGroup) {
    return item.id;
  }
}
