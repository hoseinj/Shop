import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoZone } from 'app/shared/model/geo-zone.model';
import { GeoZoneService } from './geo-zone.service';

@Component({
  selector: 'jhi-geo-zone-delete-dialog',
  templateUrl: './geo-zone-delete-dialog.component.html'
})
export class GeoZoneDeleteDialogComponent {
  geoZone: IGeoZone;

  constructor(protected geoZoneService: GeoZoneService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.geoZoneService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'geoZoneListModification',
        content: 'Deleted an geoZone'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-geo-zone-delete-popup',
  template: ''
})
export class GeoZoneDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ geoZone }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GeoZoneDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.geoZone = geoZone;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/geo-zone', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/geo-zone', { outlets: { popup: null } }]);
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
