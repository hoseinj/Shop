import { IAccessToken } from 'app/shared/model/access-token.model';

export interface IShopUser {
  id?: number;
  username?: string;
  password?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  avatar?: string;
  avatarPath?: string;
  code?: string;
  ip?: string;
  address?: string;
  phoneNumber?: number;
  name?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  accessTokens?: IAccessToken[];
  userGroupIdId?: number;
  userGroupId?: number;
}

export class ShopUser implements IShopUser {
  constructor(
    public id?: number,
    public username?: string,
    public password?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public avatar?: string,
    public avatarPath?: string,
    public code?: string,
    public ip?: string,
    public address?: string,
    public phoneNumber?: number,
    public name?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public accessTokens?: IAccessToken[],
    public userGroupIdId?: number,
    public userGroupId?: number
  ) {}
}
