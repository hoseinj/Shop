import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { OrderTotalUpdateComponent } from 'app/entities/order-total/order-total-update.component';
import { OrderTotalService } from 'app/entities/order-total/order-total.service';
import { OrderTotal } from 'app/shared/model/order-total.model';

describe('Component Tests', () => {
  describe('OrderTotal Management Update Component', () => {
    let comp: OrderTotalUpdateComponent;
    let fixture: ComponentFixture<OrderTotalUpdateComponent>;
    let service: OrderTotalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [OrderTotalUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderTotalUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderTotalUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderTotalService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderTotal(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderTotal();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
