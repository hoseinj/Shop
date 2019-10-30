import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAccessToken } from 'app/shared/model/access-token.model';

@Component({
  selector: 'jhi-access-token-detail',
  templateUrl: './access-token-detail.component.html'
})
export class AccessTokenDetailComponent implements OnInit {
  accessToken: IAccessToken;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ accessToken }) => {
      this.accessToken = accessToken;
    });
  }

  previousState() {
    window.history.back();
  }
}
