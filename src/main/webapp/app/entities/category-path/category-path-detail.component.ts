import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategoryPath } from 'app/shared/model/category-path.model';

@Component({
  selector: 'jhi-category-path-detail',
  templateUrl: './category-path-detail.component.html'
})
export class CategoryPathDetailComponent implements OnInit {
  categoryPath: ICategoryPath;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ categoryPath }) => {
      this.categoryPath = categoryPath;
    });
  }

  previousState() {
    window.history.back();
  }
}
