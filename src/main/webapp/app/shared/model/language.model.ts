export interface ILanguage {
  id?: number;
  name?: string;
  code?: string;
  image?: string;
  imagePath?: string;
  locale?: string;
  sortOrder?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
}

export class Language implements ILanguage {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string,
    public image?: string,
    public imagePath?: string,
    public locale?: string,
    public sortOrder?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string
  ) {}
}
