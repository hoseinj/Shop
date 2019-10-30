import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderHistory } from 'app/shared/model/order-history.model';

type EntityResponseType = HttpResponse<IOrderHistory>;
type EntityArrayResponseType = HttpResponse<IOrderHistory[]>;

@Injectable({ providedIn: 'root' })
export class OrderHistoryService {
  public resourceUrl = SERVER_API_URL + 'api/order-histories';

  constructor(protected http: HttpClient) {}

  create(orderHistory: IOrderHistory): Observable<EntityResponseType> {
    return this.http.post<IOrderHistory>(this.resourceUrl, orderHistory, { observe: 'response' });
  }

  update(orderHistory: IOrderHistory): Observable<EntityResponseType> {
    return this.http.put<IOrderHistory>(this.resourceUrl, orderHistory, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrderHistory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrderHistory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
