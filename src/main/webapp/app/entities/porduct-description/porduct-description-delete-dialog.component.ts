import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPorductDescription } from 'app/shared/model/porduct-description.model';
import { PorductDescriptionService } from './porduct-description.service';

@Component({
  selector: 'jhi-porduct-description-delete-dialog',
  templateUrl: './porduct-description-delete-dialog.component.html'
})
export class PorductDescriptionDeleteDialogComponent {
  porductDescription: IPorductDescription;

  constructor(
    protected porductDescriptionService: PorductDescriptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.porductDescriptionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'porductDescriptionListModification',
        content: 'Deleted an porductDescription'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-porduct-description-delete-popup',
  template: ''
})
export class PorductDescriptionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ porductDescription }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PorductDescriptionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.porductDescription = porductDescription;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/porduct-description', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/porduct-description', { outlets: { popup: null } }]);
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
