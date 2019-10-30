import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IShopPage } from 'app/shared/model/shop-page.model';

type EntityResponseType = HttpResponse<IShopPage>;
type EntityArrayResponseType = HttpResponse<IShopPage[]>;

@Injectable({ providedIn: 'root' })
export class ShopPageService {
  public resourceUrl = SERVER_API_URL + 'api/shop-pages';

  constructor(protected http: HttpClient) {}

  create(shopPage: IShopPage): Observable<EntityResponseType> {
    return this.http.post<IShopPage>(this.resourceUrl, shopPage, { observe: 'response' });
  }

  update(shopPage: IShopPage): Observable<EntityResponseType> {
    return this.http.put<IShopPage>(this.resourceUrl, shopPage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IShopPage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IShopPage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
