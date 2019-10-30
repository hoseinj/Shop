export interface ICategory {
  id?: number;
  name?: string;
  image?: string;
  imagePath?: string;
  parentInt?: number;
  sortOrder?: number;
  metaTagTitle?: string;
  metaTagDescription?: string;
  metaTagKeyword?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  productId?: number;
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public name?: string,
    public image?: string,
    public imagePath?: string,
    public parentInt?: number,
    public sortOrder?: number,
    public metaTagTitle?: string,
    public metaTagDescription?: string,
    public metaTagKeyword?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public productId?: number
  ) {}
}
