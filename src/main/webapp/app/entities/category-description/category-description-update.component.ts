import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ICategoryDescription, CategoryDescription } from 'app/shared/model/category-description.model';
import { CategoryDescriptionService } from './category-description.service';

@Component({
  selector: 'jhi-category-description-update',
  templateUrl: './category-description-update.component.html'
})
export class CategoryDescriptionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    metaDescroption: [],
    metaKeyword: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(
    protected categoryDescriptionService: CategoryDescriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ categoryDescription }) => {
      this.updateForm(categoryDescription);
    });
  }

  updateForm(categoryDescription: ICategoryDescription) {
    this.editForm.patchValue({
      id: categoryDescription.id,
      name: categoryDescription.name,
      description: categoryDescription.description,
      metaDescroption: categoryDescription.metaDescroption,
      metaKeyword: categoryDescription.metaKeyword,
      isActive: categoryDescription.isActive,
      createdBy: categoryDescription.createdBy,
      modifiedBy: categoryDescription.modifiedBy,
      createdDate: categoryDescription.createdDate,
      modifiedDate: categoryDescription.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const categoryDescription = this.createFromForm();
    if (categoryDescription.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryDescriptionService.update(categoryDescription));
    } else {
      this.subscribeToSaveResponse(this.categoryDescriptionService.create(categoryDescription));
    }
  }

  private createFromForm(): ICategoryDescription {
    return {
      ...new CategoryDescription(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      metaDescroption: this.editForm.get(['metaDescroption']).value,
      metaKeyword: this.editForm.get(['metaKeyword']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategoryDescription>>) {
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
