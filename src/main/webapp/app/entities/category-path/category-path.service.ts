import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategoryPath } from 'app/shared/model/category-path.model';

type EntityResponseType = HttpResponse<ICategoryPath>;
type EntityArrayResponseType = HttpResponse<ICategoryPath[]>;

@Injectable({ providedIn: 'root' })
export class CategoryPathService {
  public resourceUrl = SERVER_API_URL + 'api/category-paths';

  constructor(protected http: HttpClient) {}

  create(categoryPath: ICategoryPath): Observable<EntityResponseType> {
    return this.http.post<ICategoryPath>(this.resourceUrl, categoryPath, { observe: 'response' });
  }

  update(categoryPath: ICategoryPath): Observable<EntityResponseType> {
    return this.http.put<ICategoryPath>(this.resourceUrl, categoryPath, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategoryPath>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategoryPath[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
