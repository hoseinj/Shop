export interface ISetting {
  id?: number;
  url?: string;
  metaTagTitle?: string;
  metaTagDescription?: string;
  metaTagKeywords?: string;
  storeName?: string;
  storeOwner?: string;
  storeAddress?: string;
  storeEmail?: string;
  storeTelephone?: string;
  storeFax?: string;
  storeLogo?: string;
  storeLogoPath?: string;
  maintenanceMode?: number;
  storeLanguageName?: string;
  storeImage?: string;
  storeImagePath?: string;
  google?: string;
  facebook?: string;
  twitter?: string;
  instagram?: string;
  orderStatus?: number;
  invoicePrefix?: string;
  itemsPerPage?: number;
  categoryProductCount?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  countryIdId?: number;
  zoneIdId?: number;
}

export class Setting implements ISetting {
  constructor(
    public id?: number,
    public url?: string,
    public metaTagTitle?: string,
    public metaTagDescription?: string,
    public metaTagKeywords?: string,
    public storeName?: string,
    public storeOwner?: string,
    public storeAddress?: string,
    public storeEmail?: string,
    public storeTelephone?: string,
    public storeFax?: string,
    public storeLogo?: string,
    public storeLogoPath?: string,
    public maintenanceMode?: number,
    public storeLanguageName?: string,
    public storeImage?: string,
    public storeImagePath?: string,
    public google?: string,
    public facebook?: string,
    public twitter?: string,
    public instagram?: string,
    public orderStatus?: number,
    public invoicePrefix?: string,
    public itemsPerPage?: number,
    public categoryProductCount?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public countryIdId?: number,
    public zoneIdId?: number
  ) {}
}
