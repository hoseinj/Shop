import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CustomerWishlistUpdateComponent } from 'app/entities/customer-wishlist/customer-wishlist-update.component';
import { CustomerWishlistService } from 'app/entities/customer-wishlist/customer-wishlist.service';
import { CustomerWishlist } from 'app/shared/model/customer-wishlist.model';

describe('Component Tests', () => {
  describe('CustomerWishlist Management Update Component', () => {
    let comp: CustomerWishlistUpdateComponent;
    let fixture: ComponentFixture<CustomerWishlistUpdateComponent>;
    let service: CustomerWishlistService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerWishlistUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CustomerWishlistUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustomerWishlistUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerWishlistService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustomerWishlist(123);
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
        const entity = new CustomerWishlist();
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
