import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IZone, Zone } from 'app/shared/model/zone.model';
import { ZoneService } from './zone.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';

@Component({
  selector: 'jhi-zone-update',
  templateUrl: './zone-update.component.html'
})
export class ZoneUpdateComponent implements OnInit {
  isSaving: boolean;

  countries: ICountry[];

  editForm = this.fb.group({
    id: [],
    code: [],
    name: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    countryId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected zoneService: ZoneService,
    protected countryService: CountryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ zone }) => {
      this.updateForm(zone);
    });
    this.countryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICountry[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICountry[]>) => response.body)
      )
      .subscribe((res: ICountry[]) => (this.countries = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(zone: IZone) {
    this.editForm.patchValue({
      id: zone.id,
      code: zone.code,
      name: zone.name,
      isActive: zone.isActive,
      createdBy: zone.createdBy,
      modifiedBy: zone.modifiedBy,
      createdDate: zone.createdDate,
      modifiedDate: zone.modifiedDate,
      countryId: zone.countryId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const zone = this.createFromForm();
    if (zone.id !== undefined) {
      this.subscribeToSaveResponse(this.zoneService.update(zone));
    } else {
      this.subscribeToSaveResponse(this.zoneService.create(zone));
    }
  }

  private createFromForm(): IZone {
    return {
      ...new Zone(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      name: this.editForm.get(['name']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      countryId: this.editForm.get(['countryId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IZone>>) {
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

  trackCountryById(index: number, item: ICountry) {
    return item.id;
  }
}
