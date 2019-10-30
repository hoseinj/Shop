export interface IAddress {
  id?: number;
  firstName?: string;
  lastName?: string;
  company?: string;
  password?: string;
  address1?: string;
  address2?: string;
  postcode?: string;
  city?: string;
  state?: string;
  addressType?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  customerIdId?: number;
  countryIdId?: number;
  zoneIdId?: number;
}

export class Address implements IAddress {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public company?: string,
    public password?: string,
    public address1?: string,
    public address2?: string,
    public postcode?: string,
    public city?: string,
    public state?: string,
    public addressType?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public customerIdId?: number,
    public countryIdId?: number,
    public zoneIdId?: number
  ) {}
}
