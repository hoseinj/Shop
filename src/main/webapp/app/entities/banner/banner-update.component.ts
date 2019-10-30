import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IBanner, Banner } from 'app/shared/model/banner.model';
import { BannerService } from './banner.service';
import { IBannerGroup } from 'app/shared/model/banner-group.model';
import { BannerGroupService } from 'app/entities/banner-group/banner-group.service';

@Component({
  selector: 'jhi-banner-update',
  templateUrl: './banner-update.component.html'
})
export class BannerUpdateComponent implements OnInit {
  isSaving: boolean;

  bannergroups: IBannerGroup[];

  editForm = this.fb.group({
    id: [],
    title: [],
    url: [],
    link: [],
    content: [],
    position: [],
    image: [],
    imagePath: [],
    containerName: [],
    viewPageCount: [],
    isActive: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: [],
    bannerGroupIdId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected bannerService: BannerService,
    protected bannerGroupService: BannerGroupService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ banner }) => {
      this.updateForm(banner);
    });
    this.bannerGroupService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBannerGroup[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBannerGroup[]>) => response.body)
      )
      .subscribe((res: IBannerGroup[]) => (this.bannergroups = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(banner: IBanner) {
    this.editForm.patchValue({
      id: banner.id,
      title: banner.title,
      url: banner.url,
      link: banner.link,
      content: banner.content,
      position: banner.position,
      image: banner.image,
      imagePath: banner.imagePath,
      containerName: banner.containerName,
      viewPageCount: banner.viewPageCount,
      isActive: banner.isActive,
      createdBy: banner.createdBy,
      modifiedBy: banner.modifiedBy,
      createdDate: banner.createdDate,
      modifiedDate: banner.modifiedDate,
      bannerGroupIdId: banner.bannerGroupIdId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const banner = this.createFromForm();
    if (banner.id !== undefined) {
      this.subscribeToSaveResponse(this.bannerService.update(banner));
    } else {
      this.subscribeToSaveResponse(this.bannerService.create(banner));
    }
  }

  private createFromForm(): IBanner {
    return {
      ...new Banner(),
      id: this.editForm.get(['id']).value,
      title: this.editForm.get(['title']).value,
      url: this.editForm.get(['url']).value,
      link: this.editForm.get(['link']).value,
      content: this.editForm.get(['content']).value,
      position: this.editForm.get(['position']).value,
      image: this.editForm.get(['image']).value,
      imagePath: this.editForm.get(['imagePath']).value,
      containerName: this.editForm.get(['containerName']).value,
      viewPageCount: this.editForm.get(['viewPageCount']).value,
      isActive: this.editForm.get(['isActive']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value,
      bannerGroupIdId: this.editForm.get(['bannerGroupIdId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBanner>>) {
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

  trackBannerGroupById(index: number, item: IBannerGroup) {
    return item.id;
  }
}
