export interface IManufacturer {
  id?: number;
  name?: string;
  image?: string;
  imagePath?: string;
  sortOrder?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class Manufacturer implements IManufacturer {
  constructor(
    public id?: number,
    public name?: string,
    public image?: string,
    public imagePath?: string,
    public sortOrder?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
