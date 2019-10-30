import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ProductViewLogUpdateComponent } from 'app/entities/product-view-log/product-view-log-update.component';
import { ProductViewLogService } from 'app/entities/product-view-log/product-view-log.service';
import { ProductViewLog } from 'app/shared/model/product-view-log.model';

describe('Component Tests', () => {
  describe('ProductViewLog Management Update Component', () => {
    let comp: ProductViewLogUpdateComponent;
    let fixture: ComponentFixture<ProductViewLogUpdateComponent>;
    let service: ProductViewLogService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductViewLogUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProductViewLogUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductViewLogUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductViewLogService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductViewLog(123);
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
        const entity = new ProductViewLog();
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
