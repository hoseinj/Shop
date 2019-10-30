import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ILanguage, Language } from 'app/shared/model/language.model';
import { LanguageService } from './language.service';

@Component({
  selector: 'jhi-language-update',
  templateUrl: './language-update.component.html'
})
export class LanguageUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    code: [],
    image: [],
    imagePath: [],
    locale: [],
    sortOrder: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected languageService: LanguageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ language }) => {
      this.updateForm(language);
    });
  }

  updateForm(language: ILanguage) {
    this.editForm.patchValue({
      id: language.id,
      name: language.name,
      code: language.code,
      image: language.image,
      imagePath: language.imagePath,
      locale: language.locale,
      sortOrder: language.sortOrder,
      isActive: language.isActive,
      createdBy: language.createdBy,
      modifiedBy: language.modifiedBy,
      createdDate: language.createdDate,
      modifiedDate: language.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const language = this.createFromForm();
    if (language.id !== undefined) {
      this.subscribeToSaveResponse(this.languageService.update(language));
    } else {
      this.subscribeToSaveResponse(this.languageService.create(language));
    }
  }

  private createFromForm(): ILanguage {
    return {
      ...new Language(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      code: this.editForm.get(['code']).value,
      image: this.editForm.get(['image']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      locale: this.editForm.get(['locale']).value,
      sortOrder: this.editForm.get(['sortOrder']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILanguage>>) {
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
