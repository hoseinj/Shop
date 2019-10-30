export interface ICountry {
  id?: number;
  name?: string;
  isoCode2?: string;
  isoCode3?: string;
  addressFormat?: string;
  postCodeRequired?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class Country implements ICountry {
  constructor(
    public id?: number,
    public name?: string,
    public isoCode2?: string,
    public isoCode3?: string,
    public addressFormat?: string,
    public postCodeRequired?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
