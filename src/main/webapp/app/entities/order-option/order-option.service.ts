import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrderOption } from 'app/shared/model/order-option.model';

type EntityResponseType = HttpResponse<IOrderOption>;
type EntityArrayResponseType = HttpResponse<IOrderOption[]>;

@Injectable({ providedIn: 'root' })
export class OrderOptionService {
  public resourceUrl = SERVER_API_URL + 'api/order-options';

  constructor(protected http: HttpClient) {}

  create(orderOption: IOrderOption): Observable<EntityResponseType> {
    return this.http.post<IOrderOption>(this.resourceUrl, orderOption, { observe: 'response' });
  }

  update(orderOption: IOrderOption): Observable<EntityResponseType> {
    return this.http.put<IOrderOption>(this.resourceUrl, orderOption, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrderOption>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrderOption[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
