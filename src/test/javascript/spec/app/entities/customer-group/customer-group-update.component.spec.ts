import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CustomerGroupUpdateComponent } from 'app/entities/customer-group/customer-group-update.component';
import { CustomerGroupService } from 'app/entities/customer-group/customer-group.service';
import { CustomerGroup } from 'app/shared/model/customer-group.model';

describe('Component Tests', () => {
  describe('CustomerGroup Management Update Component', () => {
    let comp: CustomerGroupUpdateComponent;
    let fixture: ComponentFixture<CustomerGroupUpdateComponent>;
    let service: CustomerGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustomerGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustomerGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustomerGroup(123);
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
        const entity = new CustomerGroup();
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
