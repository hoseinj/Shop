import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategoryDescription } from 'app/shared/model/category-description.model';

type EntityResponseType = HttpResponse<ICategoryDescription>;
type EntityArrayResponseType = HttpResponse<ICategoryDescription[]>;

@Injectable({ providedIn: 'root' })
export class CategoryDescriptionService {
  public resourceUrl = SERVER_API_URL + 'api/category-descriptions';

  constructor(protected http: HttpClient) {}

  create(categoryDescription: ICategoryDescription): Observable<EntityResponseType> {
    return this.http.post<ICategoryDescription>(this.resourceUrl, categoryDescription, { observe: 'response' });
  }

  update(categoryDescription: ICategoryDescription): Observable<EntityResponseType> {
    return this.http.put<ICategoryDescription>(this.resourceUrl, categoryDescription, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategoryDescription>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategoryDescription[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
