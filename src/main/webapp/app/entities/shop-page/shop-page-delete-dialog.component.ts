import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShopPage } from 'app/shared/model/shop-page.model';
import { ShopPageService } from './shop-page.service';

@Component({
  selector: 'jhi-shop-page-delete-dialog',
  templateUrl: './shop-page-delete-dialog.component.html'
})
export class ShopPageDeleteDialogComponent {
  shopPage: IShopPage;

  constructor(protected shopPageService: ShopPageService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.shopPageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'shopPageListModification',
        content: 'Deleted an shopPage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-shop-page-delete-popup',
  template: ''
})
export class ShopPageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ shopPage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ShopPageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.shopPage = shopPage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/shop-page', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/shop-page', { outlets: { popup: null } }]);
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
