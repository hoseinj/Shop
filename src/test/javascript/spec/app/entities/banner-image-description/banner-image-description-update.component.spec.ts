import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { BannerImageDescriptionUpdateComponent } from 'app/entities/banner-image-description/banner-image-description-update.component';
import { BannerImageDescriptionService } from 'app/entities/banner-image-description/banner-image-description.service';
import { BannerImageDescription } from 'app/shared/model/banner-image-description.model';

describe('Component Tests', () => {
  describe('BannerImageDescription Management Update Component', () => {
    let comp: BannerImageDescriptionUpdateComponent;
    let fixture: ComponentFixture<BannerImageDescriptionUpdateComponent>;
    let service: BannerImageDescriptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [BannerImageDescriptionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BannerImageDescriptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BannerImageDescriptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BannerImageDescriptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BannerImageDescription(123);
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
        const entity = new BannerImageDescription();
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
