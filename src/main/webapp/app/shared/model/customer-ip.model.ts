export interface ICustomerIp {
  id?: number;
  ip?: string;
  dateAdded?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class CustomerIp implements ICustomerIp {
  constructor(
    public id?: number,
    public ip?: string,
    public dateAdded?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
