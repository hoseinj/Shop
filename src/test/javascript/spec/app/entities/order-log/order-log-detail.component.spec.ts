import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { OrderLogDetailComponent } from 'app/entities/order-log/order-log-detail.component';
import { OrderLog } from 'app/shared/model/order-log.model';

describe('Component Tests', () => {
  describe('OrderLog Management Detail Component', () => {
    let comp: OrderLogDetailComponent;
    let fixture: ComponentFixture<OrderLogDetailComponent>;
    const route = ({ data: of({ orderLog: new OrderLog(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [OrderLogDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderLogDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderLogDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderLog).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
