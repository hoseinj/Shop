export interface IProductDiscount {
  id?: number;
  quantity?: number;
  priority?: number;
  pirce?: number;
  dateStart?: string;
  dateEnd?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class ProductDiscount implements IProductDiscount {
  constructor(
    public id?: number,
    public quantity?: number,
    public priority?: number,
    public pirce?: number,
    public dateStart?: string,
    public dateEnd?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
