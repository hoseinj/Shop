import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { AccessTokenUpdateComponent } from 'app/entities/access-token/access-token-update.component';
import { AccessTokenService } from 'app/entities/access-token/access-token.service';
import { AccessToken } from 'app/shared/model/access-token.model';

describe('Component Tests', () => {
  describe('AccessToken Management Update Component', () => {
    let comp: AccessTokenUpdateComponent;
    let fixture: ComponentFixture<AccessTokenUpdateComponent>;
    let service: AccessTokenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [AccessTokenUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AccessTokenUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AccessTokenUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AccessTokenService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AccessToken(123);
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
        const entity = new AccessToken();
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
