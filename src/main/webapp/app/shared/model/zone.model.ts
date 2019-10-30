export interface IZone {
  id?: number;
  code?: string;
  name?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  countryId?: number;
}

export class Zone implements IZone {
  constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public countryId?: number
  ) {}
}
