import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShopUser } from 'app/shared/model/shop-user.model';
import { ShopUserService } from './shop-user.service';

@Component({
  selector: 'jhi-shop-user-delete-dialog',
  templateUrl: './shop-user-delete-dialog.component.html'
})
export class ShopUserDeleteDialogComponent {
  shopUser: IShopUser;

  constructor(protected shopUserService: ShopUserService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.shopUserService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'shopUserListModification',
        content: 'Deleted an shopUser'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-shop-user-delete-popup',
  template: ''
})
export class ShopUserDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ shopUser }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ShopUserDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.shopUser = shopUser;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/shop-user', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/shop-user', { outlets: { popup: null } }]);
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
