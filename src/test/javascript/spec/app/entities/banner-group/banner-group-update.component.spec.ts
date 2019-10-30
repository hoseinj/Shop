import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { BannerGroupUpdateComponent } from 'app/entities/banner-group/banner-group-update.component';
import { BannerGroupService } from 'app/entities/banner-group/banner-group.service';
import { BannerGroup } from 'app/shared/model/banner-group.model';

describe('Component Tests', () => {
  describe('BannerGroup Management Update Component', () => {
    let comp: BannerGroupUpdateComponent;
    let fixture: ComponentFixture<BannerGroupUpdateComponent>;
    let service: BannerGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [BannerGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BannerGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BannerGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BannerGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BannerGroup(123);
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
        const entity = new BannerGroup();
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
