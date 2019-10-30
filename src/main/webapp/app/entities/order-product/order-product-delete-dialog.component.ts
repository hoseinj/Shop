import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderProduct } from 'app/shared/model/order-product.model';
import { OrderProductService } from './order-product.service';

@Component({
  selector: 'jhi-order-product-delete-dialog',
  templateUrl: './order-product-delete-dialog.component.html'
})
export class OrderProductDeleteDialogComponent {
  orderProduct: IOrderProduct;

  constructor(
    protected orderProductService: OrderProductService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.orderProductService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'orderProductListModification',
        content: 'Deleted an orderProduct'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-order-product-delete-popup',
  template: ''
})
export class OrderProductDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderProduct }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OrderProductDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.orderProduct = orderProduct;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/order-product', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/order-product', { outlets: { popup: null } }]);
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
