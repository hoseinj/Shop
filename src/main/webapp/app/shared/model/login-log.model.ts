export interface ILoginLog {
  id?: number;
  firstName?: string;
  ipAddress?: string;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class LoginLog implements ILoginLog {
  constructor(
    public id?: number,
    public firstName?: string,
    public ipAddress?: string,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
