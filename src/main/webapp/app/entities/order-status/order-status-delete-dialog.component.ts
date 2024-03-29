import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderStatus } from 'app/shared/model/order-status.model';
import { OrderStatusService } from './order-status.service';

@Component({
  selector: 'jhi-order-status-delete-dialog',
  templateUrl: './order-status-delete-dialog.component.html'
})
export class OrderStatusDeleteDialogComponent {
  orderStatus: IOrderStatus;

  constructor(
    protected orderStatusService: OrderStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.orderStatusService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'orderStatusListModification',
        content: 'Deleted an orderStatus'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-order-status-delete-popup',
  template: ''
})
export class OrderStatusDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderStatus }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OrderStatusDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.orderStatus = orderStatus;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/order-status', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/order-status', { outlets: { popup: null } }]);
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
