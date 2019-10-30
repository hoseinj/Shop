export interface IShopPage {
  id?: number;
  title?: string;
  intro?: string;
  fullText?: string;
  sortOrder?: number;
  metaTagTitle?: string;
  metaTagDescroption?: string;
  metaTagKeywords?: string;
  viewPageCount?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class ShopPage implements IShopPage {
  constructor(
    public id?: number,
    public title?: string,
    public intro?: string,
    public fullText?: string,
    public sortOrder?: number,
    public metaTagTitle?: string,
    public metaTagDescroption?: string,
    public metaTagKeywords?: string,
    public viewPageCount?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
