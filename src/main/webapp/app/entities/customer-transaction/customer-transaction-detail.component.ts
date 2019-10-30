import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustomerTransaction } from 'app/shared/model/customer-transaction.model';

@Component({
  selector: 'jhi-customer-transaction-detail',
  templateUrl: './customer-transaction-detail.component.html'
})
export class CustomerTransactionDetailComponent implements OnInit {
  customerTransaction: ICustomerTransaction;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerTransaction }) => {
      this.customerTransaction = customerTransaction;
    });
  }

  previousState() {
    window.history.back();
  }
}
