import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { OrderTotalDetailComponent } from 'app/entities/order-total/order-total-detail.component';
import { OrderTotal } from 'app/shared/model/order-total.model';

describe('Component Tests', () => {
  describe('OrderTotal Management Detail Component', () => {
    let comp: OrderTotalDetailComponent;
    let fixture: ComponentFixture<OrderTotalDetailComponent>;
    const route = ({ data: of({ orderTotal: new OrderTotal(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [OrderTotalDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderTotalDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderTotalDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderTotal).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
