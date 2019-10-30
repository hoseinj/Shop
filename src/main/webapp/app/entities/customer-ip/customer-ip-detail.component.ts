import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustomerIp } from 'app/shared/model/customer-ip.model';

@Component({
  selector: 'jhi-customer-ip-detail',
  templateUrl: './customer-ip-detail.component.html'
})
export class CustomerIpDetailComponent implements OnInit {
  customerIp: ICustomerIp;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ customerIp }) => {
      this.customerIp = customerIp;
    });
  }

  previousState() {
    window.history.back();
  }
}
