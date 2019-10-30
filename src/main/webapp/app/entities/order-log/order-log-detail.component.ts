import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderLog } from 'app/shared/model/order-log.model';

@Component({
  selector: 'jhi-order-log-detail',
  templateUrl: './order-log-detail.component.html'
})
export class OrderLogDetailComponent implements OnInit {
  orderLog: IOrderLog;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ orderLog }) => {
      this.orderLog = orderLog;
    });
  }

  previousState() {
    window.history.back();
  }
}
