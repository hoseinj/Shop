import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductRating } from 'app/shared/model/product-rating.model';

@Component({
  selector: 'jhi-product-rating-detail',
  templateUrl: './product-rating-detail.component.html'
})
export class ProductRatingDetailComponent implements OnInit {
  productRating: IProductRating;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ productRating }) => {
      this.productRating = productRating;
    });
  }

  previousState() {
    window.history.back();
  }
}
