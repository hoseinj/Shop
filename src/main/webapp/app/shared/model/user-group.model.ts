import { IShopUser } from 'app/shared/model/shop-user.model';

export interface IUserGroup {
  id?: number;
  name?: string;
  slug?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  shopUsers?: IShopUser[];
}

export class UserGroup implements IUserGroup {
  constructor(
    public id?: number,
    public name?: string,
    public slug?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public shopUsers?: IShopUser[]
  ) {}
}
