import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategoryPath } from 'app/shared/model/category-path.model';
import { CategoryPathService } from './category-path.service';

@Component({
  selector: 'jhi-category-path-delete-dialog',
  templateUrl: './category-path-delete-dialog.component.html'
})
export class CategoryPathDeleteDialogComponent {
  categoryPath: ICategoryPath;

  constructor(
    protected categoryPathService: CategoryPathService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.categoryPathService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'categoryPathListModification',
        content: 'Deleted an categoryPath'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-category-path-delete-popup',
  template: ''
})
export class CategoryPathDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ categoryPath }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CategoryPathDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.categoryPath = categoryPath;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/category-path', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/category-path', { outlets: { popup: null } }]);
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
