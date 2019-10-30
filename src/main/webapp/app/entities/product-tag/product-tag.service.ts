import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductTag } from 'app/shared/model/product-tag.model';

type EntityResponseType = HttpResponse<IProductTag>;
type EntityArrayResponseType = HttpResponse<IProductTag[]>;

@Injectable({ providedIn: 'root' })
export class ProductTagService {
  public resourceUrl = SERVER_API_URL + 'api/product-tags';

  constructor(protected http: HttpClient) {}

  create(productTag: IProductTag): Observable<EntityResponseType> {
    return this.http.post<IProductTag>(this.resourceUrl, productTag, { observe: 'response' });
  }

  update(productTag: IProductTag): Observable<EntityResponseType> {
    return this.http.put<IProductTag>(this.resourceUrl, productTag, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProductTag>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProductTag[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
