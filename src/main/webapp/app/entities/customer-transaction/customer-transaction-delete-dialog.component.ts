import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustomerTransaction } from 'app/shared/model/customer-transaction.model';
import { CustomerTransactionService } from './customer-transaction.service';

@Component({
  selector: 'jhi-customer-transaction-delete-dialog',
  templateUrl: './customer-transaction-delete-dialog.component.html'
})
export class CustomerTransactionDeleteDialogComponent {
  customerTransaction: ICustomerTransaction;

  constructor(
    protected customerTransactionService: CustomerTransactionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.customerTransactionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'customerTransactionListModification',
        content: 'Deleted an customerTransaction'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-customer-transaction-delete-popup',
  template: ''
})
export class CustomerTransactionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerTransaction }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CustomerTransactionDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.customerTransaction = customerTransaction;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/customer-transaction', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/customer-transaction', { outlets: { popup: null } }]);
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
