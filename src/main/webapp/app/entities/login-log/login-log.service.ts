import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILoginLog } from 'app/shared/model/login-log.model';

type EntityResponseType = HttpResponse<ILoginLog>;
type EntityArrayResponseType = HttpResponse<ILoginLog[]>;

@Injectable({ providedIn: 'root' })
export class LoginLogService {
  public resourceUrl = SERVER_API_URL + 'api/login-logs';

  constructor(protected http: HttpClient) {}

  create(loginLog: ILoginLog): Observable<EntityResponseType> {
    return this.http.post<ILoginLog>(this.resourceUrl, loginLog, { observe: 'response' });
  }

  update(loginLog: ILoginLog): Observable<EntityResponseType> {
    return this.http.put<ILoginLog>(this.resourceUrl, loginLog, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILoginLog>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILoginLog[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
