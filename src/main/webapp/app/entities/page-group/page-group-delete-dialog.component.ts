import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPageGroup } from 'app/shared/model/page-group.model';
import { PageGroupService } from './page-group.service';

@Component({
  selector: 'jhi-page-group-delete-dialog',
  templateUrl: './page-group-delete-dialog.component.html'
})
export class PageGroupDeleteDialogComponent {
  pageGroup: IPageGroup;

  constructor(protected pageGroupService: PageGroupService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.pageGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'pageGroupListModification',
        content: 'Deleted an pageGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-page-group-delete-popup',
  template: ''
})
export class PageGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pageGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PageGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.pageGroup = pageGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/page-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/page-group', { outlets: { popup: null } }]);
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
