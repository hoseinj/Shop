import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { GeoZoneUpdateComponent } from 'app/entities/geo-zone/geo-zone-update.component';
import { GeoZoneService } from 'app/entities/geo-zone/geo-zone.service';
import { GeoZone } from 'app/shared/model/geo-zone.model';

describe('Component Tests', () => {
  describe('GeoZone Management Update Component', () => {
    let comp: GeoZoneUpdateComponent;
    let fixture: ComponentFixture<GeoZoneUpdateComponent>;
    let service: GeoZoneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [GeoZoneUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GeoZoneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoZoneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoZoneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoZone(123);
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
        const entity = new GeoZone();
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
