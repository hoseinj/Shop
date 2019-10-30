import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderTotal } from 'app/shared/model/order-total.model';
import { OrderTotalService } from './order-total.service';

@Component({
  selector: 'jhi-order-total-delete-dialog',
  templateUrl: './order-total-delete-dialog.component.html'
})
export class OrderTotalDeleteDialogComponent {
  orderTotal: IOrderTotal;

  constructor(
    protected orderTotalService: OrderTotalService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.orderTotalService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'orderTotalListModification',
        content: 'Deleted an orderTotal'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-order-total-delete-popup',
  template: ''
})
export class OrderTotalDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderTotal }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OrderTotalDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.orderTotal = orderTotal;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/order-total', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/order-total', { outlets: { popup: null } }]);
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
