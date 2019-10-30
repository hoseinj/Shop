import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { ProductDiscountDeleteDialogComponent } from 'app/entities/product-discount/product-discount-delete-dialog.component';
import { ProductDiscountService } from 'app/entities/product-discount/product-discount.service';

describe('Component Tests', () => {
  describe('ProductDiscount Management Delete Component', () => {
    let comp: ProductDiscountDeleteDialogComponent;
    let fixture: ComponentFixture<ProductDiscountDeleteDialogComponent>;
    let service: ProductDiscountService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductDiscountDeleteDialogComponent]
      })
        .overrideTemplate(ProductDiscountDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductDiscountDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductDiscountService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
