export interface IOrderLog {
  id?: number;
  invoiceNo?: string;
  invocePerfix?: string;
  firstname?: string;
  lastname?: string;
  email?: string;
  telephone?: string;
  fax?: string;
  shippingFristname?: string;
  shippingLastname?: string;
  shippingCompany?: string;
  shippingAddress1?: string;
  shippingAddress2?: string;
  shippingCity?: string;
  shippingPostcode?: string;
  shippingCountry?: string;
  shippingZone?: string;
  shippingAddressFormat?: string;
  shippingMethod?: string;
  paymentFirstname?: string;
  paymentLastname?: string;
  paymentCompany?: string;
  paymentAddress1?: string;
  paymentAddress2?: string;
  paymentCity?: string;
  paymentPostcode?: string;
  paymentCountry?: string;
  paymentZone?: string;
  paymentAddressFormat?: string;
  paymentMethod?: string;
  comment?: string;
  total?: number;
  reward?: number;
  commision?: number;
  currencyCode?: string;
  currencyValue?: number;
  ip?: string;
  paymentFlag?: number;
  orderName?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  customerIdId?: number;
  currencyIdId?: number;
  orderStatusIdId?: number;
}

export class OrderLog implements IOrderLog {
  constructor(
    public id?: number,
    public invoiceNo?: string,
    public invocePerfix?: string,
    public firstname?: string,
    public lastname?: string,
    public email?: string,
    public telephone?: string,
    public fax?: string,
    public shippingFristname?: string,
    public shippingLastname?: string,
    public shippingCompany?: string,
    public shippingAddress1?: string,
    public shippingAddress2?: string,
    public shippingCity?: string,
    public shippingPostcode?: string,
    public shippingCountry?: string,
    public shippingZone?: string,
    public shippingAddressFormat?: string,
    public shippingMethod?: string,
    public paymentFirstname?: string,
    public paymentLastname?: string,
    public paymentCompany?: string,
    public paymentAddress1?: string,
    public paymentAddress2?: string,
    public paymentCity?: string,
    public paymentPostcode?: string,
    public paymentCountry?: string,
    public paymentZone?: string,
    public paymentAddressFormat?: string,
    public paymentMethod?: string,
    public comment?: string,
    public total?: number,
    public reward?: number,
    public commision?: number,
    public currencyCode?: string,
    public currencyValue?: number,
    public ip?: string,
    public paymentFlag?: number,
    public orderName?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public customerIdId?: number,
    public currencyIdId?: number,
    public orderStatusIdId?: number
  ) {}
}
