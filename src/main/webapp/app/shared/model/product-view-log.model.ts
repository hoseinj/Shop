export interface IProductViewLog {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  userName?: string;
  mobile?: number;
  address?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  productIdId?: number;
}

export class ProductViewLog implements IProductViewLog {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public userName?: string,
    public mobile?: number,
    public address?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public productIdId?: number
  ) {}
}
