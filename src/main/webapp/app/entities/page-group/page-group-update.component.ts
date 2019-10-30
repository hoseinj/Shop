import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPageGroup, PageGroup } from 'app/shared/model/page-group.model';
import { PageGroupService } from './page-group.service';

@Component({
  selector: 'jhi-page-group-update',
  templateUrl: './page-group-update.component.html'
})
export class PageGroupUpdateComponent implements OnInit {
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

  constructor(protected pageGroupService: PageGroupService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ pageGroup }) => {
      this.updateForm(pageGroup);
    });
  }

  updateForm(pageGroup: IPageGroup) {
    this.editForm.patchValue({
      id: pageGroup.id,
      title: pageGroup.title,
      isActive: pageGroup.isActive,
      createdBy: pageGroup.createdBy,
      modifiedBy: pageGroup.modifiedBy,
      createdDate: pageGroup.createdDate,
      modifiedDate: pageGroup.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const pageGroup = this.createFromForm();
    if (pageGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.pageGroupService.update(pageGroup));
    } else {
      this.subscribeToSaveResponse(this.pageGroupService.create(pageGroup));
    }
  }

  private createFromForm(): IPageGroup {
    return {
      ...new PageGroup(),
      id: this.editForm.get(['id']).value,
      title: this.editForm.get(['title']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPageGroup>>) {
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
