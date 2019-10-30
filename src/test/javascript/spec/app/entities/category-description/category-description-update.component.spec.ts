import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CategoryDescriptionUpdateComponent } from 'app/entities/category-description/category-description-update.component';
import { CategoryDescriptionService } from 'app/entities/category-description/category-description.service';
import { CategoryDescription } from 'app/shared/model/category-description.model';

describe('Component Tests', () => {
  describe('CategoryDescription Management Update Component', () => {
    let comp: CategoryDescriptionUpdateComponent;
    let fixture: ComponentFixture<CategoryDescriptionUpdateComponent>;
    let service: CategoryDescriptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CategoryDescriptionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CategoryDescriptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoryDescriptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoryDescriptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategoryDescription(123);
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
        const entity = new CategoryDescription();
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
