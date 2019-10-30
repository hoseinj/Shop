import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGeoZone, GeoZone } from 'app/shared/model/geo-zone.model';
import { GeoZoneService } from './geo-zone.service';

@Component({
  selector: 'jhi-geo-zone-update',
  templateUrl: './geo-zone-update.component.html'
})
export class GeoZoneUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected geoZoneService: GeoZoneService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ geoZone }) => {
      this.updateForm(geoZone);
    });
  }

  updateForm(geoZone: IGeoZone) {
    this.editForm.patchValue({
      id: geoZone.id,
      name: geoZone.name,
      description: geoZone.description,
      isActive: geoZone.isActive,
      createdBy: geoZone.createdBy,
      modifiedBy: geoZone.modifiedBy,
      createdDate: geoZone.createdDate,
      modifiedDate: geoZone.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const geoZone = this.createFromForm();
    if (geoZone.id !== undefined) {
      this.subscribeToSaveResponse(this.geoZoneService.update(geoZone));
    } else {
      this.subscribeToSaveResponse(this.geoZoneService.create(geoZone));
    }
  }

  private createFromForm(): IGeoZone {
    return {
      ...new GeoZone(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoZone>>) {
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
