import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { SettingService } from 'app/entities/setting/setting.service';
import { ISetting, Setting } from 'app/shared/model/setting.model';

describe('Service Tests', () => {
  describe('Setting Service', () => {
    let injector: TestBed;
    let service: SettingService;
    let httpMock: HttpTestingController;
    let elemDefault: ISetting;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(SettingService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Setting(
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
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
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

      it('should create a Setting', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Setting(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Setting', () => {
        const returnedFromService = Object.assign(
          {
            url: 'BBBBBB',
            metaTagTitle: 'BBBBBB',
            metaTagDescription: 'BBBBBB',
            metaTagKeywords: 'BBBBBB',
            storeName: 'BBBBBB',
            storeOwner: 'BBBBBB',
            storeAddress: 'BBBBBB',
            storeEmail: 'BBBBBB',
            storeTelephone: 'BBBBBB',
            storeFax: 'BBBBBB',
            storeLogo: 'BBBBBB',
            storeLogoPath: 'BBBBBB',
            maintenanceMode: 1,
            storeLanguageName: 'BBBBBB',
            storeImage: 'BBBBBB',
            storeImagePath: 'BBBBBB',
            google: 'BBBBBB',
            facebook: 'BBBBBB',
            twitter: 'BBBBBB',
            instagram: 'BBBBBB',
            orderStatus: 1,
            invoicePrefix: 'BBBBBB',
            itemsPerPage: 1,
            categoryProductCount: 1,
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

      it('should return a list of Setting', () => {
        const returnedFromService = Object.assign(
          {
            url: 'BBBBBB',
            metaTagTitle: 'BBBBBB',
            metaTagDescription: 'BBBBBB',
            metaTagKeywords: 'BBBBBB',
            storeName: 'BBBBBB',
            storeOwner: 'BBBBBB',
            storeAddress: 'BBBBBB',
            storeEmail: 'BBBBBB',
            storeTelephone: 'BBBBBB',
            storeFax: 'BBBBBB',
            storeLogo: 'BBBBBB',
            storeLogoPath: 'BBBBBB',
            maintenanceMode: 1,
            storeLanguageName: 'BBBBBB',
            storeImage: 'BBBBBB',
            storeImagePath: 'BBBBBB',
            google: 'BBBBBB',
            facebook: 'BBBBBB',
            twitter: 'BBBBBB',
            instagram: 'BBBBBB',
            orderStatus: 1,
            invoicePrefix: 'BBBBBB',
            itemsPerPage: 1,
            categoryProductCount: 1,
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

      it('should delete a Setting', () => {
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
