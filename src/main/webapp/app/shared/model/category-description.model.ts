export interface ICategoryDescription {
  id?: number;
  name?: string;
  description?: string;
  metaDescroption?: string;
  metaKeyword?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class CategoryDescription implements ICategoryDescription {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public metaDescroption?: string,
    public metaKeyword?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
