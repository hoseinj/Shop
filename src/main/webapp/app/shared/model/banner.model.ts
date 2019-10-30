export interface IBanner {
  id?: number;
  title?: string;
  url?: string;
  link?: string;
  content?: string;
  position?: string;
  image?: string;
  imagePath?: string;
  containerName?: string;
  viewPageCount?: string;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  bannerGroupIdId?: number;
}

export class Banner implements IBanner {
  constructor(
    public id?: number,
    public title?: string,
    public url?: string,
    public link?: string,
    public content?: string,
    public position?: string,
    public image?: string,
    public imagePath?: string,
    public containerName?: string,
    public viewPageCount?: string,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public bannerGroupIdId?: number
  ) {}
}
