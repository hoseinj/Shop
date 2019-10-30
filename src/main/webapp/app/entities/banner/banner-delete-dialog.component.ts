import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBanner } from 'app/shared/model/banner.model';
import { BannerService } from './banner.service';

@Component({
  selector: 'jhi-banner-delete-dialog',
  templateUrl: './banner-delete-dialog.component.html'
})
export class BannerDeleteDialogComponent {
  banner: IBanner;

  constructor(protected bannerService: BannerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.bannerService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'bannerListModification',
        content: 'Deleted an banner'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-banner-delete-popup',
  template: ''
})
export class BannerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ banner }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BannerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.banner = banner;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/banner', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/banner', { outlets: { popup: null } }]);
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
