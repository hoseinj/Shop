import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CategoryPathUpdateComponent } from 'app/entities/category-path/category-path-update.component';
import { CategoryPathService } from 'app/entities/category-path/category-path.service';
import { CategoryPath } from 'app/shared/model/category-path.model';

describe('Component Tests', () => {
  describe('CategoryPath Management Update Component', () => {
    let comp: CategoryPathUpdateComponent;
    let fixture: ComponentFixture<CategoryPathUpdateComponent>;
    let service: CategoryPathService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CategoryPathUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CategoryPathUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategoryPathUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoryPathService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategoryPath(123);
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
        const entity = new CategoryPath();
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
