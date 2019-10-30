import { ICategory } from 'app/shared/model/category.model';
import { IProductImage } from 'app/shared/model/product-image.model';
import { ICustomerWishlist } from 'app/shared/model/customer-wishlist.model';
import { IProductRelated } from 'app/shared/model/product-related.model';
import { IProductRating } from 'app/shared/model/product-rating.model';
import { IOrderProduct } from 'app/shared/model/order-product.model';

export interface IProduct {
  id?: number;
  sku?: string;
  upc?: string;
  quantity?: number;
  image?: string;
  imagePath?: string;
  shipping?: number;
  price?: number;
  dateAvailable?: string;
  sortOrder?: number;
  name?: string;
  description?: string;
  amount?: number;
  metaTagTitle?: string;
  metaTagDescription?: string;
  metaTagKeyword?: string;
  discount?: number;
  subtractStock?: boolean;
  minimumQuantity?: number;
  location?: string;
  wishlistStatus?: number;
  deletFlag?: string;
  isFeatured?: number;
  rating?: number;
  condition?: number;
  isActive?: number;
  createdBy?: number;
  modifiedBy?: number;
  createdDate?: string;
  modifiedDate?: string;
  categories?: ICategory[];
  productImages?: IProductImage[];
  wishLists?: ICustomerWishlist[];
  productRelatedS?: IProductRelated[];
  productRatings?: IProductRating[];
  orderProducts?: IOrderProduct[];
  stockStatusidId?: number;
  manufacturerIdId?: number;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public sku?: string,
    public upc?: string,
    public quantity?: number,
    public image?: string,
    public imagePath?: string,
    public shipping?: number,
    public price?: number,
    public dateAvailable?: string,
    public sortOrder?: number,
    public name?: string,
    public description?: string,
    public amount?: number,
    public metaTagTitle?: string,
    public metaTagDescription?: string,
    public metaTagKeyword?: string,
    public discount?: number,
    public subtractStock?: boolean,
    public minimumQuantity?: number,
    public location?: string,
    public wishlistStatus?: number,
    public deletFlag?: string,
    public isFeatured?: number,
    public rating?: number,
    public condition?: number,
    public isActive?: number,
    public createdBy?: number,
    public modifiedBy?: number,
    public createdDate?: string,
    public modifiedDate?: string,
    public categories?: ICategory[],
    public productImages?: IProductImage[],
    public wishLists?: ICustomerWishlist[],
    public productRelatedS?: IProductRelated[],
    public productRatings?: IProductRating[],
    public orderProducts?: IOrderProduct[],
    public stockStatusidId?: number,
    public manufacturerIdId?: number
  ) {
    this.subtractStock = this.subtractStock || false;
  }
}
