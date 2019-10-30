import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoZone } from 'app/shared/model/geo-zone.model';

type EntityResponseType = HttpResponse<IGeoZone>;
type EntityArrayResponseType = HttpResponse<IGeoZone[]>;

@Injectable({ providedIn: 'root' })
export class GeoZoneService {
  public resourceUrl = SERVER_API_URL + 'api/geo-zones';

  constructor(protected http: HttpClient) {}

  create(geoZone: IGeoZone): Observable<EntityResponseType> {
    return this.http.post<IGeoZone>(this.resourceUrl, geoZone, { observe: 'response' });
  }

  update(geoZone: IGeoZone): Observable<EntityResponseType> {
    return this.http.put<IGeoZone>(this.resourceUrl, geoZone, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoZone>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoZone[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
