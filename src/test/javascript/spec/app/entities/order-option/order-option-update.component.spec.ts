import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { OrderOptionUpdateComponent } from 'app/entities/order-option/order-option-update.component';
import { OrderOptionService } from 'app/entities/order-option/order-option.service';
import { OrderOption } from 'app/shared/model/order-option.model';

describe('Component Tests', () => {
  describe('OrderOption Management Update Component', () => {
    let comp: OrderOptionUpdateComponent;
    let fixture: ComponentFixture<OrderOptionUpdateComponent>;
    let service: OrderOptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [OrderOptionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderOptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderOptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderOptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderOption(123);
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
        const entity = new OrderOption();
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
