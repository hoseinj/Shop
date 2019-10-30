import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderLog } from 'app/shared/model/order-log.model';

type EntityResponseType = HttpResponse<IOrderLog>;
type EntityArrayResponseType = HttpResponse<IOrderLog[]>;

@Injectable({ providedIn: 'root' })
export class OrderLogService {
  public resourceUrl = SERVER_API_URL + 'api/order-logs';

  constructor(protected http: HttpClient) {}

  create(orderLog: IOrderLog): Observable<EntityResponseType> {
    return this.http.post<IOrderLog>(this.resourceUrl, orderLog, { observe: 'response' });
  }

  update(orderLog: IOrderLog): Observable<EntityResponseType> {
    return this.http.put<IOrderLog>(this.resourceUrl, orderLog, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrderLog>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrderLog[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
