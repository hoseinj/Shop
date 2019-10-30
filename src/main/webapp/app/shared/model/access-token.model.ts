export interface IAccessToken {
  id?: number;
  token?: string;
  shopUserId?: number;
  shopUserIdId?: number;
}

export class AccessToken implements IAccessToken {
  constructor(public id?: number, public token?: string, public shopUserId?: number, public shopUserIdId?: number) {}
}
