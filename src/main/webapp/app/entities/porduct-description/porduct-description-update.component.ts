import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPorductDescription, PorductDescription } from 'app/shared/model/porduct-description.model';
import { PorductDescriptionService } from './porduct-description.service';

@Component({
  selector: 'jhi-porduct-description-update',
  templateUrl: './porduct-description-update.component.html'
})
export class PorductDescriptionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    metaDescription: [],
    metaKeyword: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(
    protected porductDescriptionService: PorductDescriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ porductDescription }) => {
      this.updateForm(porductDescription);
    });
  }

  updateForm(porductDescription: IPorductDescription) {
    this.editForm.patchValue({
      id: porductDescription.id,
      name: porductDescription.name,
      description: porductDescription.description,
      metaDescription: porductDescription.metaDescription,
      metaKeyword: porductDescription.metaKeyword,
      isActive: porductDescription.isActive,
      createdBy: porductDescription.createdBy,
      modifiedBy: porductDescription.modifiedBy,
      createdDate: porductDescription.createdDate,
      modifiedDate: porductDescription.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const porductDescription = this.createFromForm();
    if (porductDescription.id !== undefined) {
      this.subscribeToSaveResponse(this.porductDescriptionService.update(porductDescription));
    } else {
      this.subscribeToSaveResponse(this.porductDescriptionService.create(porductDescription));
    }
  }

  private createFromForm(): IPorductDescription {
    return {
      ...new PorductDescription(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      metaDescription: this.editForm.get(['metaDescription']).value,
      metaKeyword: this.editForm.get(['metaKeyword']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPorductDescription>>) {
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
