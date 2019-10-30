import { IProductRating } from 'app/shared/model/product-rating.model';

export interface ICustomer {
  id?: number;
  firstName?: string;
  lastName?: string;
  username?: string;
  email?: string;
  password?: string;
  mobile?: string;
  address?: string;
  city?: string;
  pincode?: string;
  avatar?: string;
  avatarPath?: string;
  mailStatus?: number;
  deleteFlag?: number;
  lastlogin?: string;
  newsletter?: number;
  safe?: number;
  ip?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  productRatings?: IProductRating[];
  countryIdId?: number;
  zoneIdId?: number;
  customerGroupIdId?: number;
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public username?: string,
    public email?: string,
    public password?: string,
    public mobile?: string,
    public address?: string,
    public city?: string,
    public pincode?: string,
    public avatar?: string,
    public avatarPath?: string,
    public mailStatus?: number,
    public deleteFlag?: number,
    public lastlogin?: string,
    public newsletter?: number,
    public safe?: number,
    public ip?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public productRatings?: IProductRating[],
    public countryIdId?: number,
    public zoneIdId?: number,
    public customerGroupIdId?: number
  ) {}
}
