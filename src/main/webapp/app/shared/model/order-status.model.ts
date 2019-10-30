export interface IOrderStatus {
  id?: number;
  name?: string;
  colorCode?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class OrderStatus implements IOrderStatus {
  constructor(
    public id?: number,
    public name?: string,
    public colorCode?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
