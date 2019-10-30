import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMigrations } from 'app/shared/model/migrations.model';
import { MigrationsService } from './migrations.service';

@Component({
  selector: 'jhi-migrations-delete-dialog',
  templateUrl: './migrations-delete-dialog.component.html'
})
export class MigrationsDeleteDialogComponent {
  migrations: IMigrations;

  constructor(
    protected migrationsService: MigrationsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.migrationsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'migrationsListModification',
        content: 'Deleted an migrations'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-migrations-delete-popup',
  template: ''
})
export class MigrationsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ migrations }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MigrationsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.migrations = migrations;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/migrations', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/migrations', { outlets: { popup: null } }]);
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
