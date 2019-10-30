import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { LoginLogUpdateComponent } from 'app/entities/login-log/login-log-update.component';
import { LoginLogService } from 'app/entities/login-log/login-log.service';
import { LoginLog } from 'app/shared/model/login-log.model';

describe('Component Tests', () => {
  describe('LoginLog Management Update Component', () => {
    let comp: LoginLogUpdateComponent;
    let fixture: ComponentFixture<LoginLogUpdateComponent>;
    let service: LoginLogService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [LoginLogUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LoginLogUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LoginLogUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LoginLogService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LoginLog(123);
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
        const entity = new LoginLog();
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
