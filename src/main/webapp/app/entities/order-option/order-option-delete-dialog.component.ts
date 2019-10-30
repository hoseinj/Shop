import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderOption } from 'app/shared/model/order-option.model';
import { OrderOptionService } from './order-option.service';

@Component({
  selector: 'jhi-order-option-delete-dialog',
  templateUrl: './order-option-delete-dialog.component.html'
})
export class OrderOptionDeleteDialogComponent {
  orderOption: IOrderOption;

  constructor(
    protected orderOptionService: OrderOptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.orderOptionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'orderOptionListModification',
        content: 'Deleted an orderOption'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-order-option-delete-popup',
  template: ''
})
export class OrderOptionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderOption }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OrderOptionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.orderOption = orderOption;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/order-option', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/order-option', { outlets: { popup: null } }]);
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
