import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBanner } from 'app/shared/model/banner.model';

@Component({
  selector: 'jhi-banner-detail',
  templateUrl: './banner-detail.component.html'
})
export class BannerDetailComponent implements OnInit {
  banner: IBanner;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ banner }) => {
      this.banner = banner;
    });
  }

  previousState() {
    window.history.back();
  }
}
