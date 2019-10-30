export interface IProductRelated {
  id?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  productId?: number;
  productIdId?: number;
}

export class ProductRelated implements IProductRelated {
  constructor(
    public id?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public productId?: number,
    public productIdId?: number
  ) {}
}
