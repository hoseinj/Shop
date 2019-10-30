import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IManufacturer, Manufacturer } from 'app/shared/model/manufacturer.model';
import { ManufacturerService } from './manufacturer.service';

@Component({
  selector: 'jhi-manufacturer-update',
  templateUrl: './manufacturer-update.component.html'
})
export class ManufacturerUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    image: [],
    imagePath: [],
    sortOrder: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected manufacturerService: ManufacturerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ manufacturer }) => {
      this.updateForm(manufacturer);
    });
  }

  updateForm(manufacturer: IManufacturer) {
    this.editForm.patchValue({
      id: manufacturer.id,
      name: manufacturer.name,
      image: manufacturer.image,
      imagePath: manufacturer.imagePath,
      sortOrder: manufacturer.sortOrder,
      isActive: manufacturer.isActive,
      createdBy: manufacturer.createdBy,
      modifiedBy: manufacturer.modifiedBy,
      createdDate: manufacturer.createdDate,
      modifiedDate: manufacturer.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const manufacturer = this.createFromForm();
    if (manufacturer.id !== undefined) {
      this.subscribeToSaveResponse(this.manufacturerService.update(manufacturer));
    } else {
      this.subscribeToSaveResponse(this.manufacturerService.create(manufacturer));
    }
  }

  private createFromForm(): IManufacturer {
    return {
      ...new Manufacturer(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      image: this.editForm.get(['image']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      sortOrder: this.editForm.get(['sortOrder']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IManufacturer>>) {
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
