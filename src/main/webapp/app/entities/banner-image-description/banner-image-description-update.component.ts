import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IBannerImageDescription, BannerImageDescription } from 'app/shared/model/banner-image-description.model';
import { BannerImageDescriptionService } from './banner-image-description.service';

@Component({
  selector: 'jhi-banner-image-description-update',
  templateUrl: './banner-image-description-update.component.html'
})
export class BannerImageDescriptionUpdateComponent implements OnInit {
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

  constructor(
    protected bannerImageDescriptionService: BannerImageDescriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ bannerImageDescription }) => {
      this.updateForm(bannerImageDescription);
    });
  }

  updateForm(bannerImageDescription: IBannerImageDescription) {
    this.editForm.patchValue({
      id: bannerImageDescription.id,
      title: bannerImageDescription.title,
      isActive: bannerImageDescription.isActive,
      createdBy: bannerImageDescription.createdBy,
      modifiedBy: bannerImageDescription.modifiedBy,
      createdDate: bannerImageDescription.createdDate,
      modifiedDate: bannerImageDescription.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const bannerImageDescription = this.createFromForm();
    if (bannerImageDescription.id !== undefined) {
      this.subscribeToSaveResponse(this.bannerImageDescriptionService.update(bannerImageDescription));
    } else {
      this.subscribeToSaveResponse(this.bannerImageDescriptionService.create(bannerImageDescription));
    }
  }

  private createFromForm(): IBannerImageDescription {
    return {
      ...new BannerImageDescription(),
      id: this.editForm.get(['id']).value,
      title: this.editForm.get(['title']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBannerImageDescription>>) {
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
