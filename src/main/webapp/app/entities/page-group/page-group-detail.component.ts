import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPageGroup } from 'app/shared/model/page-group.model';

@Component({
  selector: 'jhi-page-group-detail',
  templateUrl: './page-group-detail.component.html'
})
export class PageGroupDetailComponent implements OnInit {
  pageGroup: IPageGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ pageGroup }) => {
      this.pageGroup = pageGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
