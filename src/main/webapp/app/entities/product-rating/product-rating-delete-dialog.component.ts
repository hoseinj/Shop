import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductRating } from 'app/shared/model/product-rating.model';
import { ProductRatingService } from './product-rating.service';

@Component({
  selector: 'jhi-product-rating-delete-dialog',
  templateUrl: './product-rating-delete-dialog.component.html'
})
export class ProductRatingDeleteDialogComponent {
  productRating: IProductRating;

  constructor(
    protected productRatingService: ProductRatingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.productRatingService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'productRatingListModification',
        content: 'Deleted an productRating'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-product-rating-delete-popup',
  template: ''
})
export class ProductRatingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ productRating }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProductRatingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.productRating = productRating;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/product-rating', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/product-rating', { outlets: { popup: null } }]);
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
