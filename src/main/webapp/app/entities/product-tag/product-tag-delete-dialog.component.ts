import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductTag } from 'app/shared/model/product-tag.model';
import { ProductTagService } from './product-tag.service';

@Component({
  selector: 'jhi-product-tag-delete-dialog',
  templateUrl: './product-tag-delete-dialog.component.html'
})
export class ProductTagDeleteDialogComponent {
  productTag: IProductTag;

  constructor(
    protected productTagService: ProductTagService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.productTagService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'productTagListModification',
        content: 'Deleted an productTag'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-product-tag-delete-popup',
  template: ''
})
export class ProductTagDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ productTag }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProductTagDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.productTag = productTag;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/product-tag', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/product-tag', { outlets: { popup: null } }]);
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
