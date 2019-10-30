import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAccessToken } from 'app/shared/model/access-token.model';
import { AccessTokenService } from './access-token.service';

@Component({
  selector: 'jhi-access-token-delete-dialog',
  templateUrl: './access-token-delete-dialog.component.html'
})
export class AccessTokenDeleteDialogComponent {
  accessToken: IAccessToken;

  constructor(
    protected accessTokenService: AccessTokenService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.accessTokenService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'accessTokenListModification',
        content: 'Deleted an accessToken'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-access-token-delete-popup',
  template: ''
})
export class AccessTokenDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ accessToken }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AccessTokenDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.accessToken = accessToken;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/access-token', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/access-token', { outlets: { popup: null } }]);
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
