import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductDiscount } from 'app/shared/model/product-discount.model';

type EntityResponseType = HttpResponse<IProductDiscount>;
type EntityArrayResponseType = HttpResponse<IProductDiscount[]>;

@Injectable({ providedIn: 'root' })
export class ProductDiscountService {
  public resourceUrl = SERVER_API_URL + 'api/product-discounts';

  constructor(protected http: HttpClient) {}

  create(productDiscount: IProductDiscount): Observable<EntityResponseType> {
    return this.http.post<IProductDiscount>(this.resourceUrl, productDiscount, { observe: 'response' });
  }

  update(productDiscount: IProductDiscount): Observable<EntityResponseType> {
    return this.http.put<IProductDiscount>(this.resourceUrl, productDiscount, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProductDiscount>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProductDiscount[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
