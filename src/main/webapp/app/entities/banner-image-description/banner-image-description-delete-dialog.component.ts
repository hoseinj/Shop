import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBannerImageDescription } from 'app/shared/model/banner-image-description.model';
import { BannerImageDescriptionService } from './banner-image-description.service';

@Component({
  selector: 'jhi-banner-image-description-delete-dialog',
  templateUrl: './banner-image-description-delete-dialog.component.html'
})
export class BannerImageDescriptionDeleteDialogComponent {
  bannerImageDescription: IBannerImageDescription;

  constructor(
    protected bannerImageDescriptionService: BannerImageDescriptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.bannerImageDescriptionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'bannerImageDescriptionListModification',
        content: 'Deleted an bannerImageDescription'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-banner-image-description-delete-popup',
  template: ''
})
export class BannerImageDescriptionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bannerImageDescription }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BannerImageDescriptionDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.bannerImageDescription = bannerImageDescription;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/banner-image-description', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/banner-image-description', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
