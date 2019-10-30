import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustomerGroup } from 'app/shared/model/customer-group.model';
import { CustomerGroupService } from './customer-group.service';

@Component({
  selector: 'jhi-customer-group-delete-dialog',
  templateUrl: './customer-group-delete-dialog.component.html'
})
export class CustomerGroupDeleteDialogComponent {
  customerGroup: ICustomerGroup;

  constructor(
    protected customerGroupService: CustomerGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.customerGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'customerGroupListModification',
        content: 'Deleted an customerGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-customer-group-delete-popup',
  template: ''
})
export class CustomerGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CustomerGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.customerGroup = customerGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/customer-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/customer-group', { outlets: { popup: null } }]);
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
