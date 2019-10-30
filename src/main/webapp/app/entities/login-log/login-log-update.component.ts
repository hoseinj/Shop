import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ILoginLog, LoginLog } from 'app/shared/model/login-log.model';
import { LoginLogService } from './login-log.service';

@Component({
  selector: 'jhi-login-log-update',
  templateUrl: './login-log-update.component.html'
})
export class LoginLogUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    firstName: [],
    ipAddress: [],
    createdBy: [],
    modifiedBy: [],
    createdDate: [],
    modifiedDate: []
  });

  constructor(protected loginLogService: LoginLogService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ loginLog }) => {
      this.updateForm(loginLog);
    });
  }

  updateForm(loginLog: ILoginLog) {
    this.editForm.patchValue({
      id: loginLog.id,
      firstName: loginLog.firstName,
      ipAddress: loginLog.ipAddress,
      createdBy: loginLog.createdBy,
      modifiedBy: loginLog.modifiedBy,
      createdDate: loginLog.createdDate,
      modifiedDate: loginLog.modifiedDate
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const loginLog = this.createFromForm();
    if (loginLog.id !== undefined) {
      this.subscribeToSaveResponse(this.loginLogService.update(loginLog));
    } else {
      this.subscribeToSaveResponse(this.loginLogService.create(loginLog));
    }
  }

  private createFromForm(): ILoginLog {
    return {
      ...new LoginLog(),
      id: this.editForm.get(['id']).value,
      firstName: this.editForm.get(['firstName']).value,
      ipAddress: this.editForm.get(['ipAddress']).value,
      createdBy: this.editForm.get(['createdBy']).value,
      modifiedBy: this.editForm.get(['modifiedBy']).value,
      createdDate: this.editForm.get(['createdDate']).value,
      modifiedDate: this.editForm.get(['modifiedDate']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILoginLog>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
