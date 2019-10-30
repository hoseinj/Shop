import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustomerGroup } from 'app/shared/model/customer-group.model';

@Component({
  selector: 'jhi-customer-group-detail',
  templateUrl: './customer-group-detail.component.html'
})
export class CustomerGroupDetailComponent implements OnInit {
  customerGroup: ICustomerGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerGroup }) => {
      this.customerGroup = customerGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
