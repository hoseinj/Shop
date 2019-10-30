import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBannerGroup } from 'app/shared/model/banner-group.model';
import { BannerGroupService } from './banner-group.service';

@Component({
  selector: 'jhi-banner-group-delete-dialog',
  templateUrl: './banner-group-delete-dialog.component.html'
})
export class BannerGroupDeleteDialogComponent {
  bannerGroup: IBannerGroup;

  constructor(
    protected bannerGroupService: BannerGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.bannerGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'bannerGroupListModification',
        content: 'Deleted an bannerGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-banner-group-delete-popup',
  template: ''
})
export class BannerGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bannerGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BannerGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.bannerGroup = bannerGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/banner-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/banner-group', { outlets: { popup: null } }]);
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
