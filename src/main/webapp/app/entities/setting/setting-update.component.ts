import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ISetting, Setting } from 'app/shared/model/setting.model';
import { SettingService } from './setting.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';
import { IZone } from 'app/shared/model/zone.model';
import { ZoneService } from 'app/entities/zone/zone.service';

@Component({
  selector: 'jhi-setting-update',
  templateUrl: './setting-update.component.html'
})
export class SettingUpdateComponent implements OnInit {
  isSaving: boolean;

  countries: ICountry[];

  zones: IZone[];

  editForm = this.fb.group({
    id: [],
    url: [],
    metaTagTitle: [],
    metaTagDescription: [],
    metaTagKeywords: [],
    storeName: [],
    storeOwner: [],
    storeAddress: [],
    storeEmail: [],
    storeTelephone: [],
    storeFax: [],
    storeLogo: [],
    storeLogoPath: [],
    maintenanceMode: [],
    storeLanguageName: [],
    storeImage: [],
    storeImagePath: [],
    google: [],
    facebook: [],
    twitter: [],
    instagram: [],
    orderStatus: [],
    invoicePrefix: [],
    itemsPerPage: [],
    categoryProductCount: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    countryIdId: [],
    zoneIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected settingService: SettingService,
    protected countryService: CountryService,
    protected zoneService: ZoneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ setting }) => {
      this.updateForm(setting);
    });
    this.countryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICountry[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICountry[]>) => response.body)
      )
      .subscribe((res: ICountry[]) => (this.countries = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.zoneService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IZone[]>) => mayBeOk.ok),
        map((response: HttpResponse<IZone[]>) => response.body)
      )
      .subscribe((res: IZone[]) => (this.zones = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(setting: ISetting) {
    this.editForm.patchValue({
      id: setting.id,
      url: setting.url,
      metaTagTitle: setting.metaTagTitle,
      metaTagDescription: setting.metaTagDescription,
      metaTagKeywords: setting.metaTagKeywords,
      storeName: setting.storeName,
      storeOwner: setting.storeOwner,
      storeAddress: setting.storeAddress,
      storeEmail: setting.storeEmail,
      storeTelephone: setting.storeTelephone,
      storeFax: setting.storeFax,
      storeLogo: setting.storeLogo,
      storeLogoPath: setting.storeLogoPath,
      maintenanceMode: setting.maintenanceMode,
      storeLanguageName: setting.storeLanguageName,
      storeImage: setting.storeImage,
      storeImagePath: setting.storeImagePath,
      google: setting.google,
      facebook: setting.facebook,
      twitter: setting.twitter,
      instagram: setting.instagram,
      orderStatus: setting.orderStatus,
      invoicePrefix: setting.invoicePrefix,
      itemsPerPage: setting.itemsPerPage,
      categoryProductCount: setting.categoryProductCount,
      isActive: setting.isActive,
      createdBy: setting.createdBy,
      modifiedBy: setting.modifiedBy,
      createdDate: setting.createdDate,
      modifiedDate: setting.modifiedDate,
      countryIdId: setting.countryIdId,
      zoneIdId: setting.zoneIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const setting = this.createFromForm();
    if (setting.id !== undefined) {
      this.subscribeToSaveResponse(this.settingService.update(setting));
    } else {
      this.subscribeToSaveResponse(this.settingService.create(setting));
    }
  }

  private createFromForm(): ISetting {
    return {
      ...new Setting(),
      id: this.editForm.get(['id']).value,
      url: this.editForm.get(['url']).value,
      metaTagTitle: this.editForm.get(['metaTagTitle']).value,
      metaTagDescription: this.editForm.get(['metaTagDescription']).value,
      metaTagKeywords: this.editForm.get(['metaTagKeywords']).value,
      storeName: this.editForm.get(['storeName']).value,
      storeOwner: this.editForm.get(['storeOwner']).value,
      storeAddress: this.editForm.get(['storeAddress']).value,
      storeEmail: this.editForm.get(['storeEmail']).value,
      storeTelephone: this.editForm.get(['storeTelephone']).value,
      storeFax: this.editForm.get(['storeFax']).value,
      storeLogo: this.editForm.get(['storeLogo']).value,
      storeLogoPath: this.editForm.get(['storeLogoPath']).value,
      maintenanceMode: this.editForm.get(['maintenanceMode']).value,
      storeLanguageName: this.editForm.get(['storeLanguageName']).value,
      storeImage: this.editForm.get(['storeImage']).value,
      storeImagePath: this.editForm.get(['storeImagePath']).value,
      google: this.editForm.get(['google']).value,
      facebook: this.editForm.get(['facebook']).value,
      twitter: this.editForm.get(['twitter']).value,
      instagram: this.editForm.get(['instagram']).value,
      orderStatus: this.editForm.get(['orderStatus']).value,
      invoicePrefix: this.editForm.get(['invoicePrefix']).value,
      itemsPerPage: this.editForm.get(['itemsPerPage']).value,
      categoryProductCount: this.editForm.get(['categoryProductCount']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      countryIdId: this.editForm.get(['countryIdId']).value,
      zoneIdId: this.editForm.get(['zoneIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISetting>>) {
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

  trackZoneById(index: number, item: IZone) {
    return item.id;
  }
}
