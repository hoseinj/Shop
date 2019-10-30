import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderLog } from 'app/shared/model/order-log.model';
import { OrderLogService } from './order-log.service';

@Component({
  selector: 'jhi-order-log-delete-dialog',
  templateUrl: './order-log-delete-dialog.component.html'
})
export class OrderLogDeleteDialogComponent {
  orderLog: IOrderLog;

  constructor(protected orderLogService: OrderLogService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.orderLogService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'orderLogListModification',
        content: 'Deleted an orderLog'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-order-log-delete-popup',
  template: ''
})
export class OrderLogDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderLog }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OrderLogDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.orderLog = orderLog;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/order-log', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/order-log', { outlets: { popup: null } }]);
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
