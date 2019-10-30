import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustomerWishlist } from 'app/shared/model/customer-wishlist.model';

@Component({
  selector: 'jhi-customer-wishlist-detail',
  templateUrl: './customer-wishlist-detail.component.html'
})
export class CustomerWishlistDetailComponent implements OnInit {
  customerWishlist: ICustomerWishlist;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerWishlist }) => {
      this.customerWishlist = customerWishlist;
    });
  }

  previousState() {
    window.history.back();
  }
}
