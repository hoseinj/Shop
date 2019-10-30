import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ShopUserUpdateComponent } from 'app/entities/shop-user/shop-user-update.component';
import { ShopUserService } from 'app/entities/shop-user/shop-user.service';
import { ShopUser } from 'app/shared/model/shop-user.model';

describe('Component Tests', () => {
  describe('ShopUser Management Update Component', () => {
    let comp: ShopUserUpdateComponent;
    let fixture: ComponentFixture<ShopUserUpdateComponent>;
    let service: ShopUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ShopUserUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ShopUserUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShopUserUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShopUserService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShopUser(123);
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
        const entity = new ShopUser();
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
