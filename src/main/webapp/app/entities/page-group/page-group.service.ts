import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPageGroup } from 'app/shared/model/page-group.model';

type EntityResponseType = HttpResponse<IPageGroup>;
type EntityArrayResponseType = HttpResponse<IPageGroup[]>;

@Injectable({ providedIn: 'root' })
export class PageGroupService {
  public resourceUrl = SERVER_API_URL + 'api/page-groups';

  constructor(protected http: HttpClient) {}

  create(pageGroup: IPageGroup): Observable<EntityResponseType> {
    return this.http.post<IPageGroup>(this.resourceUrl, pageGroup, { observe: 'response' });
  }

  update(pageGroup: IPageGroup): Observable<EntityResponseType> {
    return this.http.put<IPageGroup>(this.resourceUrl, pageGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPageGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPageGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
