import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILoginLog } from 'app/shared/model/login-log.model';

@Component({
  selector: 'jhi-login-log-detail',
  templateUrl: './login-log-detail.component.html'
})
export class LoginLogDetailComponent implements OnInit {
  loginLog: ILoginLog;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ loginLog }) => {
      this.loginLog = loginLog;
    });
  }

  previousState() {
    window.history.back();
  }
}
