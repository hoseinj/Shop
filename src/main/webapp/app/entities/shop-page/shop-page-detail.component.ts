import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShopPage } from 'app/shared/model/shop-page.model';

@Component({
  selector: 'jhi-shop-page-detail',
  templateUrl: './shop-page-detail.component.html'
})
export class ShopPageDetailComponent implements OnInit {
  shopPage: IShopPage;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ shopPage }) => {
      this.shopPage = shopPage;
    });
  }

  previousState() {
    window.history.back();
  }
}
