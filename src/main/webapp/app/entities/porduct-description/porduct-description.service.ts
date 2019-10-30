import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPorductDescription } from 'app/shared/model/porduct-description.model';

type EntityResponseType = HttpResponse<IPorductDescription>;
type EntityArrayResponseType = HttpResponse<IPorductDescription[]>;

@Injectable({ providedIn: 'root' })
export class PorductDescriptionService {
  public resourceUrl = SERVER_API_URL + 'api/porduct-descriptions';

  constructor(protected http: HttpClient) {}

  create(porductDescription: IPorductDescription): Observable<EntityResponseType> {
    return this.http.post<IPorductDescription>(this.resourceUrl, porductDescription, { observe: 'response' });
  }

  update(porductDescription: IPorductDescription): Observable<EntityResponseType> {
    return this.http.put<IPorductDescription>(this.resourceUrl, porductDescription, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPorductDescription>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPorductDescription[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
