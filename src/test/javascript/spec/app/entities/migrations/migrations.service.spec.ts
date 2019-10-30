import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { MigrationsService } from 'app/entities/migrations/migrations.service';
import { IMigrations, Migrations } from 'app/shared/model/migrations.model';

describe('Service Tests', () => {
  describe('Migrations Service', () => {
    let injector: TestBed;
    let service: MigrationsService;
    let httpMock: HttpTestingController;
    let elemDefault: IMigrations;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MigrationsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Migrations(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
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

      it('should create a Migrations', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Migrations(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Migrations', () => {
        const returnedFromService = Object.assign(
          {
            timestamp: 1,
            name: 'BBBBBB',
            invoiceNo: 'BBBBBB',
            invoicePrefix: 'BBBBBB',
            firstname: 'BBBBBB',
            lastname: 'BBBBBB',
            email: 'BBBBBB',
            telephone: 'BBBBBB',
            fax: 'BBBBBB',
            shippingFirstname: 'BBBBBB',
            shippingLastname: 'BBBBBB',
            shippingCompany: 'BBBBBB',
            shippingAddress1: 'BBBBBB',
            shippingAddress2: 'BBBBBB',
            shippingCity: 'BBBBBB',
            shippingPostcode: 'BBBBBB',
            shippingCountry: 'BBBBBB',
            shippingZone: 'BBBBBB',
            shippingAddressFormat: 'BBBBBB',
            shippingMethod: 'BBBBBB',
            paymentFirstname: 'BBBBBB',
            paymentLastname: 'BBBBBB',
            paymentCompany: 'BBBBBB',
            paymentAddress1: 'BBBBBB',
            paymentAddress2: 'BBBBBB',
            paymentCity: 'BBBBBB',
            paymentPostcode: 'BBBBBB',
            paymentCountry: 'BBBBBB',
            paymentZone: 'BBBBBB',
            paymentAddressFormat: 'BBBBBB',
            paymentMethod: 'BBBBBB',
            comment: 'BBBBBB',
            total: 1,
            reward: 1,
            commision: 1,
            currencyCode: 'BBBBBB',
            currencyValue: 1,
            ip: 'BBBBBB',
            paymentFlag: 1,
            orderName: 'BBBBBB',
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

      it('should return a list of Migrations', () => {
        const returnedFromService = Object.assign(
          {
            timestamp: 1,
            name: 'BBBBBB',
            invoiceNo: 'BBBBBB',
            invoicePrefix: 'BBBBBB',
            firstname: 'BBBBBB',
            lastname: 'BBBBBB',
            email: 'BBBBBB',
            telephone: 'BBBBBB',
            fax: 'BBBBBB',
            shippingFirstname: 'BBBBBB',
            shippingLastname: 'BBBBBB',
            shippingCompany: 'BBBBBB',
            shippingAddress1: 'BBBBBB',
            shippingAddress2: 'BBBBBB',
            shippingCity: 'BBBBBB',
            shippingPostcode: 'BBBBBB',
            shippingCountry: 'BBBBBB',
            shippingZone: 'BBBBBB',
            shippingAddressFormat: 'BBBBBB',
            shippingMethod: 'BBBBBB',
            paymentFirstname: 'BBBBBB',
            paymentLastname: 'BBBBBB',
            paymentCompany: 'BBBBBB',
            paymentAddress1: 'BBBBBB',
            paymentAddress2: 'BBBBBB',
            paymentCity: 'BBBBBB',
            paymentPostcode: 'BBBBBB',
            paymentCountry: 'BBBBBB',
            paymentZone: 'BBBBBB',
            paymentAddressFormat: 'BBBBBB',
            paymentMethod: 'BBBBBB',
            comment: 'BBBBBB',
            total: 1,
            reward: 1,
            commision: 1,
            currencyCode: 'BBBBBB',
            currencyValue: 1,
            ip: 'BBBBBB',
            paymentFlag: 1,
            orderName: 'BBBBBB',
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

      it('should delete a Migrations', () => {
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
