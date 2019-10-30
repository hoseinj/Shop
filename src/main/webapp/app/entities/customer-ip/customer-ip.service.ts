import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustomerIp } from 'app/shared/model/customer-ip.model';

type EntityResponseType = HttpResponse<ICustomerIp>;
type EntityArrayResponseType = HttpResponse<ICustomerIp[]>;

@Injectable({ providedIn: 'root' })
export class CustomerIpService {
  public resourceUrl = SERVER_API_URL + 'api/customer-ips';

  constructor(protected http: HttpClient) {}

  create(customerIp: ICustomerIp): Observable<EntityResponseType> {
    return this.http.post<ICustomerIp>(this.resourceUrl, customerIp, { observe: 'response' });
  }

  update(customerIp: ICustomerIp): Observable<EntityResponseType> {
    return this.http.put<ICustomerIp>(this.resourceUrl, customerIp, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICustomerIp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICustomerIp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
