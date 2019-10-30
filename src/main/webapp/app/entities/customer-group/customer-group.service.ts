import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustomerGroup } from 'app/shared/model/customer-group.model';

type EntityResponseType = HttpResponse<ICustomerGroup>;
type EntityArrayResponseType = HttpResponse<ICustomerGroup[]>;

@Injectable({ providedIn: 'root' })
export class CustomerGroupService {
  public resourceUrl = SERVER_API_URL + 'api/customer-groups';

  constructor(protected http: HttpClient) {}

  create(customerGroup: ICustomerGroup): Observable<EntityResponseType> {
    return this.http.post<ICustomerGroup>(this.resourceUrl, customerGroup, { observe: 'response' });
  }

  update(customerGroup: ICustomerGroup): Observable<EntityResponseType> {
    return this.http.put<ICustomerGroup>(this.resourceUrl, customerGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICustomerGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICustomerGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
