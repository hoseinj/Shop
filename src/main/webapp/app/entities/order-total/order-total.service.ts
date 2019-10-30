import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderTotal } from 'app/shared/model/order-total.model';

type EntityResponseType = HttpResponse<IOrderTotal>;
type EntityArrayResponseType = HttpResponse<IOrderTotal[]>;

@Injectable({ providedIn: 'root' })
export class OrderTotalService {
  public resourceUrl = SERVER_API_URL + 'api/order-totals';

  constructor(protected http: HttpClient) {}

  create(orderTotal: IOrderTotal): Observable<EntityResponseType> {
    return this.http.post<IOrderTotal>(this.resourceUrl, orderTotal, { observe: 'response' });
  }

  update(orderTotal: IOrderTotal): Observable<EntityResponseType> {
    return this.http.put<IOrderTotal>(this.resourceUrl, orderTotal, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrderTotal>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrderTotal[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
