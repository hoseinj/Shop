import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBannerGroup } from 'app/shared/model/banner-group.model';

@Component({
  selector: 'jhi-banner-group-detail',
  templateUrl: './banner-group-detail.component.html'
})
export class BannerGroupDetailComponent implements OnInit {
  bannerGroup: IBannerGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bannerGroup }) => {
      this.bannerGroup = bannerGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
