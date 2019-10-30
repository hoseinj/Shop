import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBannerImageDescription } from 'app/shared/model/banner-image-description.model';

type EntityResponseType = HttpResponse<IBannerImageDescription>;
type EntityArrayResponseType = HttpResponse<IBannerImageDescription[]>;

@Injectable({ providedIn: 'root' })
export class BannerImageDescriptionService {
  public resourceUrl = SERVER_API_URL + 'api/banner-image-descriptions';

  constructor(protected http: HttpClient) {}

  create(bannerImageDescription: IBannerImageDescription): Observable<EntityResponseType> {
    return this.http.post<IBannerImageDescription>(this.resourceUrl, bannerImageDescription, { observe: 'response' });
  }

  update(bannerImageDescription: IBannerImageDescription): Observable<EntityResponseType> {
    return this.http.put<IBannerImageDescription>(this.resourceUrl, bannerImageDescription, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBannerImageDescription>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBannerImageDescription[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
