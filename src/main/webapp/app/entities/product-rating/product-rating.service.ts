import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductRating } from 'app/shared/model/product-rating.model';

type EntityResponseType = HttpResponse<IProductRating>;
type EntityArrayResponseType = HttpResponse<IProductRating[]>;

@Injectable({ providedIn: 'root' })
export class ProductRatingService {
  public resourceUrl = SERVER_API_URL + 'api/product-ratings';

  constructor(protected http: HttpClient) {}

  create(productRating: IProductRating): Observable<EntityResponseType> {
    return this.http.post<IProductRating>(this.resourceUrl, productRating, { observe: 'response' });
  }

  update(productRating: IProductRating): Observable<EntityResponseType> {
    return this.http.put<IProductRating>(this.resourceUrl, productRating, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProductRating>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProductRating[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
