export interface IOrderOption {
  id?: number;
  name?: string;
  value?: string;
  type?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class OrderOption implements IOrderOption {
  constructor(
    public id?: number,
    public name?: string,
    public value?: string,
    public type?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
