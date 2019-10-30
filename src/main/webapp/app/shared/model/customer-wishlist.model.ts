export interface ICustomerWishlist {
  id?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  customerIdId?: number;
  productIdId?: number;
  productId?: number;
}

export class CustomerWishlist implements ICustomerWishlist {
  constructor(
    public id?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public customerIdId?: number,
    public productIdId?: number,
    public productId?: number
  ) {}
}
