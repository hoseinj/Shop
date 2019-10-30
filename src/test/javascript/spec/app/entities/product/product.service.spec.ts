import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { ProductService } from 'app/entities/product/product.service';
import { IProduct, Product } from 'app/shared/model/product.model';

describe('Service Tests', () => {
  describe('Product Service', () => {
    let injector: TestBed;
    let service: ProductService;
    let httpMock: HttpTestingController;
    let elemDefault: IProduct;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProductService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Product(
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Product', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Product(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Product', () => {
        const returnedFromService = Object.assign(
          {
            sku: 'BBBBBB',
            upc: 'BBBBBB',
            quantity: 1,
            image: 'BBBBBB',
            imagePath: 'BBBBBB',
            shipping: 1,
            price: 1,
            dateAvailable: 'BBBBBB',
            sortOrder: 1,
            name: 'BBBBBB',
            description: 'BBBBBB',
            amount: 1,
            metaTagTitle: 'BBBBBB',
            metaTagDescription: 'BBBBBB',
            metaTagKeyword: 'BBBBBB',
            discount: 1,
            subtractStock: true,
            minimumQuantity: 1,
            location: 'BBBBBB',
            wishlistStatus: 1,
            deletFlag: 'BBBBBB',
            isFeatured: 1,
            rating: 1,
            condition: 1,
            isActive: 1,
            createdBy: 1,
            modifiedBy: 1,
            createdDate: 'BBBBBB',
            modifiedDate: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Product', () => {
        const returnedFromService = Object.assign(
          {
            sku: 'BBBBBB',
            upc: 'BBBBBB',
            quantity: 1,
            image: 'BBBBBB',
            imagePath: 'BBBBBB',
            shipping: 1,
            price: 1,
            dateAvailable: 'BBBBBB',
            sortOrder: 1,
            name: 'BBBBBB',
            description: 'BBBBBB',
            amount: 1,
            metaTagTitle: 'BBBBBB',
            metaTagDescription: 'BBBBBB',
            metaTagKeyword: 'BBBBBB',
            discount: 1,
            subtractStock: true,
            minimumQuantity: 1,
            location: 'BBBBBB',
            wishlistStatus: 1,
            deletFlag: 'BBBBBB',
            isFeatured: 1,
            rating: 1,
            condition: 1,
            isActive: 1,
            createdBy: 1,
            modifiedBy: 1,
            createdDate: 'BBBBBB',
            modifiedDate: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Product', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
