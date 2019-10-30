import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { PorductDescriptionUpdateComponent } from 'app/entities/porduct-description/porduct-description-update.component';
import { PorductDescriptionService } from 'app/entities/porduct-description/porduct-description.service';
import { PorductDescription } from 'app/shared/model/porduct-description.model';

describe('Component Tests', () => {
  describe('PorductDescription Management Update Component', () => {
    let comp: PorductDescriptionUpdateComponent;
    let fixture: ComponentFixture<PorductDescriptionUpdateComponent>;
    let service: PorductDescriptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [PorductDescriptionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PorductDescriptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PorductDescriptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PorductDescriptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PorductDescription(123);
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
        const entity = new PorductDescription();
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
