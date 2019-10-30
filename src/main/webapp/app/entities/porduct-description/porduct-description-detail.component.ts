import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPorductDescription } from 'app/shared/model/porduct-description.model';

@Component({
  selector: 'jhi-porduct-description-detail',
  templateUrl: './porduct-description-detail.component.html'
})
export class PorductDescriptionDetailComponent implements OnInit {
  porductDescription: IPorductDescription;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ porductDescription }) => {
      this.porductDescription = porductDescription;
    });
  }

  previousState() {
    window.history.back();
  }
}
