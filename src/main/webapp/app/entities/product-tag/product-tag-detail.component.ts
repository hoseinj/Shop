import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductTag } from 'app/shared/model/product-tag.model';

@Component({
  selector: 'jhi-product-tag-detail',
  templateUrl: './product-tag-detail.component.html'
})
export class ProductTagDetailComponent implements OnInit {
  productTag: IProductTag;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ productTag }) => {
      this.productTag = productTag;
    });
  }

  previousState() {
    window.history.back();
  }
}
