import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IUserGroup, UserGroup } from 'app/shared/model/user-group.model';
import { UserGroupService } from './user-group.service';

@Component({
  selector: 'jhi-user-group-update',
  templateUrl: './user-group-update.component.html'
})
export class UserGroupUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    slug: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected userGroupService: UserGroupService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ userGroup }) => {
      this.updateForm(userGroup);
    });
  }

  updateForm(userGroup: IUserGroup) {
    this.editForm.patchValue({
      id: userGroup.id,
      name: userGroup.name,
      slug: userGroup.slug,
      isActive: userGroup.isActive,
      createdBy: userGroup.createdBy,
      modifiedBy: userGroup.modifiedBy,
      createdDate: userGroup.createdDate,
      modifiedDate: userGroup.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const userGroup = this.createFromForm();
    if (userGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.userGroupService.update(userGroup));
    } else {
      this.subscribeToSaveResponse(this.userGroupService.create(userGroup));
    }
  }

  private createFromForm(): IUserGroup {
    return {
      ...new UserGroup(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      slug: this.editForm.get(['slug']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserGroup>>) {
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
