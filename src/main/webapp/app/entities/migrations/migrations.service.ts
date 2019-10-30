import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMigrations } from 'app/shared/model/migrations.model';

type EntityResponseType = HttpResponse<IMigrations>;
type EntityArrayResponseType = HttpResponse<IMigrations[]>;

@Injectable({ providedIn: 'root' })
export class MigrationsService {
  public resourceUrl = SERVER_API_URL + 'api/migrations';

  constructor(protected http: HttpClient) {}

  create(migrations: IMigrations): Observable<EntityResponseType> {
    return this.http.post<IMigrations>(this.resourceUrl, migrations, { observe: 'response' });
  }

  update(migrations: IMigrations): Observable<EntityResponseType> {
    return this.http.put<IMigrations>(this.resourceUrl, migrations, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMigrations>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMigrations[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
