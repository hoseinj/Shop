import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { PageGroupUpdateComponent } from 'app/entities/page-group/page-group-update.component';
import { PageGroupService } from 'app/entities/page-group/page-group.service';
import { PageGroup } from 'app/shared/model/page-group.model';

describe('Component Tests', () => {
  describe('PageGroup Management Update Component', () => {
    let comp: PageGroupUpdateComponent;
    let fixture: ComponentFixture<PageGroupUpdateComponent>;
    let service: PageGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [PageGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PageGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PageGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PageGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PageGroup(123);
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
        const entity = new PageGroup();
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
