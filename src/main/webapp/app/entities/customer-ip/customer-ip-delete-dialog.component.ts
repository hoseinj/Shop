import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustomerIp } from 'app/shared/model/customer-ip.model';
import { CustomerIpService } from './customer-ip.service';

@Component({
  selector: 'jhi-customer-ip-delete-dialog',
  templateUrl: './customer-ip-delete-dialog.component.html'
})
export class CustomerIpDeleteDialogComponent {
  customerIp: ICustomerIp;

  constructor(
    protected customerIpService: CustomerIpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.customerIpService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'customerIpListModification',
        content: 'Deleted an customerIp'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-customer-ip-delete-popup',
  template: ''
})
export class CustomerIpDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerIp }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CustomerIpDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.customerIp = customerIp;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/customer-ip', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/customer-ip', { outlets: { popup: null } }]);
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
