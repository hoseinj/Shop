import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISetting } from 'app/shared/model/setting.model';

@Component({
  selector: 'jhi-setting-detail',
  templateUrl: './setting-detail.component.html'
})
export class SettingDetailComponent implements OnInit {
  setting: ISetting;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ setting }) => {
      this.setting = setting;
    });
  }

  previousState() {
    window.history.back();
  }
}
