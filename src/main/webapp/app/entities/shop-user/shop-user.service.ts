import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IShopUser } from 'app/shared/model/shop-user.model';

type EntityResponseType = HttpResponse<IShopUser>;
type EntityArrayResponseType = HttpResponse<IShopUser[]>;

@Injectable({ providedIn: 'root' })
export class ShopUserService {
  public resourceUrl = SERVER_API_URL + 'api/shop-users';

  constructor(protected http: HttpClient) {}

  create(shopUser: IShopUser): Observable<EntityResponseType> {
    return this.http.post<IShopUser>(this.resourceUrl, shopUser, { observe: 'response' });
  }

  update(shopUser: IShopUser): Observable<EntityResponseType> {
    return this.http.put<IShopUser>(this.resourceUrl, shopUser, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IShopUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IShopUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
