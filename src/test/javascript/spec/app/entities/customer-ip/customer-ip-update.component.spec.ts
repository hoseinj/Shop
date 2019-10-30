import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CustomerIpUpdateComponent } from 'app/entities/customer-ip/customer-ip-update.component';
import { CustomerIpService } from 'app/entities/customer-ip/customer-ip.service';
import { CustomerIp } from 'app/shared/model/customer-ip.model';

describe('Component Tests', () => {
  describe('CustomerIp Management Update Component', () => {
    let comp: CustomerIpUpdateComponent;
    let fixture: ComponentFixture<CustomerIpUpdateComponent>;
    let service: CustomerIpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerIpUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustomerIpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustomerIpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerIpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustomerIp(123);
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
        const entity = new CustomerIp();
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
