export interface IBannerGroup {
  id?: number;
  title?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class BannerGroup implements IBannerGroup {
  constructor(
    public id?: number,
    public title?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
