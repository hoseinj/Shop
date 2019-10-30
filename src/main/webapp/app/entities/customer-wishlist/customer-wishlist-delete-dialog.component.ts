import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustomerWishlist } from 'app/shared/model/customer-wishlist.model';
import { CustomerWishlistService } from './customer-wishlist.service';

@Component({
  selector: 'jhi-customer-wishlist-delete-dialog',
  templateUrl: './customer-wishlist-delete-dialog.component.html'
})
export class CustomerWishlistDeleteDialogComponent {
  customerWishlist: ICustomerWishlist;

  constructor(
    protected customerWishlistService: CustomerWishlistService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.customerWishlistService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'customerWishlistListModification',
        content: 'Deleted an customerWishlist'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-customer-wishlist-delete-popup',
  template: ''
})
export class CustomerWishlistDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerWishlist }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CustomerWishlistDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.customerWishlist = customerWishlist;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/customer-wishlist', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/customer-wishlist', { outlets: { popup: null } }]);
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
