export interface IProductRating {
  id?: number;
  firstname?: string;
  lastname?: string;
  email?: string;
  rating?: number;
  review?: string;
  imagePath?: string;
  image?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  productId?: number;
  productIdId?: number;
  customerIdId?: number;
  customerId?: number;
}

export class ProductRating implements IProductRating {
  constructor(
    public id?: number,
    public firstname?: string,
    public lastname?: string,
    public email?: string,
    public rating?: number,
    public review?: string,
    public imagePath?: string,
    public image?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public productId?: number,
    public productIdId?: number,
    public customerIdId?: number,
    public customerId?: number
  ) {}
}
