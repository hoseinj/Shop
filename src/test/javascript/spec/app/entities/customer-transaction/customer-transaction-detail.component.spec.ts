import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CustomerTransactionDetailComponent } from 'app/entities/customer-transaction/customer-transaction-detail.component';
import { CustomerTransaction } from 'app/shared/model/customer-transaction.model';

describe('Component Tests', () => {
  describe('CustomerTransaction Management Detail Component', () => {
    let comp: CustomerTransactionDetailComponent;
    let fixture: ComponentFixture<CustomerTransactionDetailComponent>;
    const route = ({ data: of({ customerTransaction: new CustomerTransaction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerTransactionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustomerTransactionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerTransactionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.customerTransaction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
