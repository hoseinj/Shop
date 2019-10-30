import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductRelated } from 'app/shared/model/product-related.model';
import { ProductRelatedService } from './product-related.service';

@Component({
  selector: 'jhi-product-related-delete-dialog',
  templateUrl: './product-related-delete-dialog.component.html'
})
export class ProductRelatedDeleteDialogComponent {
  productRelated: IProductRelated;

  constructor(
    protected productRelatedService: ProductRelatedService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.productRelatedService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'productRelatedListModification',
        content: 'Deleted an productRelated'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-product-related-delete-popup',
  template: ''
})
export class ProductRelatedDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ productRelated }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProductRelatedDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.productRelated = productRelated;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/product-related', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/product-related', { outlets: { popup: null } }]);
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
