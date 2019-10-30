export interface IGeoZone {
  id?: number;
  name?: string;
  description?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class GeoZone implements IGeoZone {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
