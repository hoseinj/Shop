export interface ICustomerTransaction {
  id?: number;
  description?: string;
  amount?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class CustomerTransaction implements ICustomerTransaction {
  constructor(
    public id?: number,
    public description?: string,
    public amount?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
