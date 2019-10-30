import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ProductRatingUpdateComponent } from 'app/entities/product-rating/product-rating-update.component';
import { ProductRatingService } from 'app/entities/product-rating/product-rating.service';
import { ProductRating } from 'app/shared/model/product-rating.model';

describe('Component Tests', () => {
  describe('ProductRating Management Update Component', () => {
    let comp: ProductRatingUpdateComponent;
    let fixture: ComponentFixture<ProductRatingUpdateComponent>;
    let service: ProductRatingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductRatingUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductRatingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductRatingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductRatingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductRating(123);
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
        const entity = new ProductRating();
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
