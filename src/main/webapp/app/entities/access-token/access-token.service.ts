import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAccessToken } from 'app/shared/model/access-token.model';

type EntityResponseType = HttpResponse<IAccessToken>;
type EntityArrayResponseType = HttpResponse<IAccessToken[]>;

@Injectable({ providedIn: 'root' })
export class AccessTokenService {
  public resourceUrl = SERVER_API_URL + 'api/access-tokens';

  constructor(protected http: HttpClient) {}

  create(accessToken: IAccessToken): Observable<EntityResponseType> {
    return this.http.post<IAccessToken>(this.resourceUrl, accessToken, { observe: 'response' });
  }

  update(accessToken: IAccessToken): Observable<EntityResponseType> {
    return this.http.put<IAccessToken>(this.resourceUrl, accessToken, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAccessToken>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAccessToken[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
