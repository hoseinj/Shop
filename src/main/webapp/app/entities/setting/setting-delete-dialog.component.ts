import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISetting } from 'app/shared/model/setting.model';
import { SettingService } from './setting.service';

@Component({
  selector: 'jhi-setting-delete-dialog',
  templateUrl: './setting-delete-dialog.component.html'
})
export class SettingDeleteDialogComponent {
  setting: ISetting;

  constructor(protected settingService: SettingService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.settingService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'settingListModification',
        content: 'Deleted an setting'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-setting-delete-popup',
  template: ''
})
export class SettingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ setting }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SettingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.setting = setting;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/setting', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/setting', { outlets: { popup: null } }]);
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
