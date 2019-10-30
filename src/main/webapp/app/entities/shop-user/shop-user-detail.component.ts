import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShopUser } from 'app/shared/model/shop-user.model';

@Component({
  selector: 'jhi-shop-user-detail',
  templateUrl: './shop-user-detail.component.html'
})
export class ShopUserDetailComponent implements OnInit {
  shopUser: IShopUser;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ shopUser }) => {
      this.shopUser = shopUser;
    });
  }

  previousState() {
    window.history.back();
  }
}
