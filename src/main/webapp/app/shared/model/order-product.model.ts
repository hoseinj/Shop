export interface IOrderProduct {
  id?: number;
  name?: string;
  model?: string;
  quantity?: number;
  trace?: number;
  total?: number;
  tax?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  productIdId?: number;
  orderIdId?: number;
  productId?: number;
  orderId?: number;
}

export class OrderProduct implements IOrderProduct {
  constructor(
    public id?: number,
    public name?: string,
    public model?: string,
    public quantity?: number,
    public trace?: number,
    public total?: number,
    public tax?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public productIdId?: number,
    public orderIdId?: number,
    public productId?: number,
    public orderId?: number
  ) {}
}
