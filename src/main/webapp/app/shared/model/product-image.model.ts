export interface IProductImage {
  id?: number;
  image?: string;
  containerName?: string;
  defualtImage?: number;
  sortOrder?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  productId?: number;
  productIdId?: number;
}

export class ProductImage implements IProductImage {
  constructor(
    public id?: number,
    public image?: string,
    public containerName?: string,
    public defualtImage?: number,
    public sortOrder?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public productId?: number,
    public productIdId?: number
  ) {}
}
