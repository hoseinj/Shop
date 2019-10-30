import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoZone } from 'app/shared/model/geo-zone.model';

@Component({
  selector: 'jhi-geo-zone-detail',
  templateUrl: './geo-zone-detail.component.html'
})
export class GeoZoneDetailComponent implements OnInit {
  geoZone: IGeoZone;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ geoZone }) => {
      this.geoZone = geoZone;
    });
  }

  previousState() {
    window.history.back();
  }
}
