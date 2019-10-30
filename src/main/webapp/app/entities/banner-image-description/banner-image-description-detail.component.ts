import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBannerImageDescription } from 'app/shared/model/banner-image-description.model';

@Component({
  selector: 'jhi-banner-image-description-detail',
  templateUrl: './banner-image-description-detail.component.html'
})
export class BannerImageDescriptionDetailComponent implements OnInit {
  bannerImageDescription: IBannerImageDescription;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ bannerImageDescription }) => {
      this.bannerImageDescription = bannerImageDescription;
    });
  }

  previousState() {
    window.history.back();
  }
}
