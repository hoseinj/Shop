import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ProductTagUpdateComponent } from 'app/entities/product-tag/product-tag-update.component';
import { ProductTagService } from 'app/entities/product-tag/product-tag.service';
import { ProductTag } from 'app/shared/model/product-tag.model';

describe('Component Tests', () => {
  describe('ProductTag Management Update Component', () => {
    let comp: ProductTagUpdateComponent;
    let fixture: ComponentFixture<ProductTagUpdateComponent>;
    let service: ProductTagService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductTagUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductTagUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductTagUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductTagService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductTag(123);
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
        const entity = new ProductTag();
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
