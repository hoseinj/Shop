export interface IProductTag {
  id?: number;
  productTagname?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class ProductTag implements IProductTag {
  constructor(
    public id?: number,
    public productTagname?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
