import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILoginLog } from 'app/shared/model/login-log.model';
import { LoginLogService } from './login-log.service';

@Component({
  selector: 'jhi-login-log-delete-dialog',
  templateUrl: './login-log-delete-dialog.component.html'
})
export class LoginLogDeleteDialogComponent {
  loginLog: ILoginLog;

  constructor(protected loginLogService: LoginLogService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.loginLogService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'loginLogListModification',
        content: 'Deleted an loginLog'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-login-log-delete-popup',
  template: ''
})
export class LoginLogDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ loginLog }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(LoginLogDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.loginLog = loginLog;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/login-log', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/login-log', { outlets: { popup: null } }]);
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
