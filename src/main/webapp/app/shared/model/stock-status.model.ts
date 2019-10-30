export interface IStockStatus {
  id?: number;
  name?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class StockStatus implements IStockStatus {
  constructor(
    public id?: number,
    public name?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
