import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStockStatus } from 'app/shared/model/stock-status.model';

type EntityResponseType = HttpResponse<IStockStatus>;
type EntityArrayResponseType = HttpResponse<IStockStatus[]>;

@Injectable({ providedIn: 'root' })
export class StockStatusService {
  public resourceUrl = SERVER_API_URL + 'api/stock-statuses';

  constructor(protected http: HttpClient) {}

  create(stockStatus: IStockStatus): Observable<EntityResponseType> {
    return this.http.post<IStockStatus>(this.resourceUrl, stockStatus, { observe: 'response' });
  }

  update(stockStatus: IStockStatus): Observable<EntityResponseType> {
    return this.http.put<IStockStatus>(this.resourceUrl, stockStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStockStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStockStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
