import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderTotal } from 'app/shared/model/order-total.model';

@Component({
  selector: 'jhi-order-total-detail',
  templateUrl: './order-total-detail.component.html'
})
export class OrderTotalDetailComponent implements OnInit {
  orderTotal: IOrderTotal;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderTotal }) => {
      this.orderTotal = orderTotal;
    });
  }

  previousState() {
    window.history.back();
  }
}
