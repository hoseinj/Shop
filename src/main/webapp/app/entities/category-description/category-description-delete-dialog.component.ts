import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategoryDescription } from 'app/shared/model/category-description.model';
import { CategoryDescriptionService } from './category-description.service';

@Component({
  selector: 'jhi-category-description-delete-dialog',
  templateUrl: './category-description-delete-dialog.component.html'
})
export class CategoryDescriptionDeleteDialogComponent {
  categoryDescription: ICategoryDescription;

  constructor(
    protected categoryDescriptionService: CategoryDescriptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.categoryDescriptionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'categoryDescriptionListModification',
        content: 'Deleted an categoryDescription'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-category-description-delete-popup',
  template: ''
})
export class CategoryDescriptionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ categoryDescription }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CategoryDescriptionDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.categoryDescription = categoryDescription;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/category-description', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/category-description', { outlets: { popup: null } }]);
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
