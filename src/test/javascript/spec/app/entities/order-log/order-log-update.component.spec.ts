import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { OrderLogUpdateComponent } from 'app/entities/order-log/order-log-update.component';
import { OrderLogService } from 'app/entities/order-log/order-log.service';
import { OrderLog } from 'app/shared/model/order-log.model';

describe('Component Tests', () => {
  describe('OrderLog Management Update Component', () => {
    let comp: OrderLogUpdateComponent;
    let fixture: ComponentFixture<OrderLogUpdateComponent>;
    let service: OrderLogService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [OrderLogUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrderLogUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrderLogUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderLogService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrderLog(123);
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
        const entity = new OrderLog();
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
