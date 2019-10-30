import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { MigrationsUpdateComponent } from 'app/entities/migrations/migrations-update.component';
import { MigrationsService } from 'app/entities/migrations/migrations.service';
import { Migrations } from 'app/shared/model/migrations.model';

describe('Component Tests', () => {
  describe('Migrations Management Update Component', () => {
    let comp: MigrationsUpdateComponent;
    let fixture: ComponentFixture<MigrationsUpdateComponent>;
    let service: MigrationsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [MigrationsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MigrationsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MigrationsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MigrationsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Migrations(123);
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
        const entity = new Migrations();
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
