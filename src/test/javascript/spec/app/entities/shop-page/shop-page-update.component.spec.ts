import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ShopPageUpdateComponent } from 'app/entities/shop-page/shop-page-update.component';
import { ShopPageService } from 'app/entities/shop-page/shop-page.service';
import { ShopPage } from 'app/shared/model/shop-page.model';

describe('Component Tests', () => {
  describe('ShopPage Management Update Component', () => {
    let comp: ShopPageUpdateComponent;
    let fixture: ComponentFixture<ShopPageUpdateComponent>;
    let service: ShopPageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ShopPageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ShopPageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShopPageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShopPageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShopPage(123);
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
        const entity = new ShopPage();
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
