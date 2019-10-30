export interface ICurrency {
  id?: number;
  title?: string;
  code?: string;
  symbolLeft?: string;
  symbolRight?: string;
  decimalPlace?: number;
  value?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class Currency implements ICurrency {
  constructor(
    public id?: number,
    public title?: string,
    public code?: string,
    public symbolLeft?: string,
    public symbolRight?: string,
    public decimalPlace?: number,
    public value?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
