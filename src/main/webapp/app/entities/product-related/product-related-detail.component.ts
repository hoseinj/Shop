import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductRelated } from 'app/shared/model/product-related.model';

@Component({
  selector: 'jhi-product-related-detail',
  templateUrl: './product-related-detail.component.html'
})
export class ProductRelatedDetailComponent implements OnInit {
  productRelated: IProductRelated;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ productRelated }) => {
      this.productRelated = productRelated;
    });
  }

  previousState() {
    window.history.back();
  }
}
