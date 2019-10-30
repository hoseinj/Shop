import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductViewLog } from 'app/shared/model/product-view-log.model';

@Component({
  selector: 'jhi-product-view-log-detail',
  templateUrl: './product-view-log-detail.component.html'
})
export class ProductViewLogDetailComponent implements OnInit {
  productViewLog: IProductViewLog;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ productViewLog }) => {
      this.productViewLog = productViewLog;
    });
  }

  previousState() {
    window.history.back();
  }
}
