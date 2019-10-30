import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductViewLog } from 'app/shared/model/product-view-log.model';
import { ProductViewLogService } from './product-view-log.service';

@Component({
  selector: 'jhi-product-view-log-delete-dialog',
  templateUrl: './product-view-log-delete-dialog.component.html'
})
export class ProductViewLogDeleteDialogComponent {
  productViewLog: IProductViewLog;

  constructor(
    protected productViewLogService: ProductViewLogService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.productViewLogService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'productViewLogListModification',
        content: 'Deleted an productViewLog'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-product-view-log-delete-popup',
  template: ''
})
export class ProductViewLogDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ productViewLog }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProductViewLogDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.productViewLog = productViewLog;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/product-view-log', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/product-view-log', { outlets: { popup: null } }]);
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
