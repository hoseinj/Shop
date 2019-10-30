import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CustomerTransactionUpdateComponent } from 'app/entities/customer-transaction/customer-transaction-update.component';
import { CustomerTransactionService } from 'app/entities/customer-transaction/customer-transaction.service';
import { CustomerTransaction } from 'app/shared/model/customer-transaction.model';

describe('Component Tests', () => {
  describe('CustomerTransaction Management Update Component', () => {
    let comp: CustomerTransactionUpdateComponent;
    let fixture: ComponentFixture<CustomerTransactionUpdateComponent>;
    let service: CustomerTransactionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerTransactionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustomerTransactionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustomerTransactionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerTransactionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustomerTransaction(123);
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
        const entity = new CustomerTransaction();
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
