export interface IEmailTemplate {
  id?: number;
  shortName?: string;
  subject?: string;
  message?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class EmailTemplate implements IEmailTemplate {
  constructor(
    public id?: number,
    public shortName?: string,
    public subject?: string,
    public message?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
