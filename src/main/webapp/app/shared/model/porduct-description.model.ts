export interface IPorductDescription {
  id?: number;
  name?: string;
  description?: string;
  metaDescription?: string;
  metaKeyword?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class PorductDescription implements IPorductDescription {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public metaDescription?: string,
    public metaKeyword?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
