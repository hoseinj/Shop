import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { OrderOptionDetailComponent } from 'app/entities/order-option/order-option-detail.component';
import { OrderOption } from 'app/shared/model/order-option.model';

describe('Component Tests', () => {
  describe('OrderOption Management Detail Component', () => {
    let comp: OrderOptionDetailComponent;
    let fixture: ComponentFixture<OrderOptionDetailComponent>;
    const route = ({ data: of({ orderOption: new OrderOption(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [OrderOptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OrderOptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderOptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.orderOption).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
