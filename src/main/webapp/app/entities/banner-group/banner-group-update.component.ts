import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IBannerGroup, BannerGroup } from 'app/shared/model/banner-group.model';
import { BannerGroupService } from './banner-group.service';

@Component({
  selector: 'jhi-banner-group-update',
  templateUrl: './banner-group-update.component.html'
})
export class BannerGroupUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    title: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected bannerGroupService: BannerGroupService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ bannerGroup }) => {
      this.updateForm(bannerGroup);
    });
  }

  updateForm(bannerGroup: IBannerGroup) {
    this.editForm.patchValue({
      id: bannerGroup.id,
      title: bannerGroup.title,
      isActive: bannerGroup.isActive,
      createdBy: bannerGroup.createdBy,
      modifiedBy: bannerGroup.modifiedBy,
      createdDate: bannerGroup.createdDate,
      modifiedDate: bannerGroup.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const bannerGroup = this.createFromForm();
    if (bannerGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.bannerGroupService.update(bannerGroup));
    } else {
      this.subscribeToSaveResponse(this.bannerGroupService.create(bannerGroup));
    }
  }

  private createFromForm(): IBannerGroup {
    return {
      ...new BannerGroup(),
      id: this.editForm.get(['id']).value,
      title: this.editForm.get(['title']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBannerGroup>>) {
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
