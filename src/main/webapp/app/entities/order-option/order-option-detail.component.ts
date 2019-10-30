import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderOption } from 'app/shared/model/order-option.model';

@Component({
  selector: 'jhi-order-option-detail',
  templateUrl: './order-option-detail.component.html'
})
export class OrderOptionDetailComponent implements OnInit {
  orderOption: IOrderOption;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderOption }) => {
      this.orderOption = orderOption;
    });
  }

  previousState() {
    window.history.back();
  }
}
