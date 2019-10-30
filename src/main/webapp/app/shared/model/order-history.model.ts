export interface IOrderHistory {
  id?: number;
  notify?: string;
  comment?: string;
  dateAdded?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class OrderHistory implements IOrderHistory {
  constructor(
    public id?: number,
    public notify?: string,
    public comment?: string,
    public dateAdded?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
