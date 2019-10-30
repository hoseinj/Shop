import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ProductRelatedUpdateComponent } from 'app/entities/product-related/product-related-update.component';
import { ProductRelatedService } from 'app/entities/product-related/product-related.service';
import { ProductRelated } from 'app/shared/model/product-related.model';

describe('Component Tests', () => {
  describe('ProductRelated Management Update Component', () => {
    let comp: ProductRelatedUpdateComponent;
    let fixture: ComponentFixture<ProductRelatedUpdateComponent>;
    let service: ProductRelatedService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductRelatedUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductRelatedUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductRelatedUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductRelatedService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductRelated(123);
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
        const entity = new ProductRelated();
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
