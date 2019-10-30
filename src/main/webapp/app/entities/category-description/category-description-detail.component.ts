import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategoryDescription } from 'app/shared/model/category-description.model';

@Component({
  selector: 'jhi-category-description-detail',
  templateUrl: './category-description-detail.component.html'
})
export class CategoryDescriptionDetailComponent implements OnInit {
  categoryDescription: ICategoryDescription;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ categoryDescription }) => {
      this.categoryDescription = categoryDescription;
    });
  }

  previousState() {
    window.history.back();
  }
}
