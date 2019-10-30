import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductViewLog } from 'app/shared/model/product-view-log.model';

type EntityResponseType = HttpResponse<IProductViewLog>;
type EntityArrayResponseType = HttpResponse<IProductViewLog[]>;

@Injectable({ providedIn: 'root' })
export class ProductViewLogService {
  public resourceUrl = SERVER_API_URL + 'api/product-view-logs';

  constructor(protected http: HttpClient) {}

  create(productViewLog: IProductViewLog): Observable<EntityResponseType> {
    return this.http.post<IProductViewLog>(this.resourceUrl, productViewLog, { observe: 'response' });
  }

  update(productViewLog: IProductViewLog): Observable<EntityResponseType> {
    return this.http.put<IProductViewLog>(this.resourceUrl, productViewLog, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProductViewLog>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProductViewLog[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
