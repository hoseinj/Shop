export interface ICategoryPath {
  id?: number;
  level?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  categoryIdId?: number;
}

export class CategoryPath implements ICategoryPath {
  constructor(
    public id?: number,
    public level?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public categoryIdId?: number
  ) {}
}
