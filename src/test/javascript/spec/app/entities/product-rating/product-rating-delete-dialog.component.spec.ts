import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { ProductRatingDeleteDialogComponent } from 'app/entities/product-rating/product-rating-delete-dialog.component';
import { ProductRatingService } from 'app/entities/product-rating/product-rating.service';

describe('Component Tests', () => {
  describe('ProductRating Management Delete Component', () => {
    let comp: ProductRatingDeleteDialogComponent;
    let fixture: ComponentFixture<ProductRatingDeleteDialogComponent>;
    let service: ProductRatingService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductRatingDeleteDialogComponent]
      })
        .overrideTemplate(ProductRatingDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductRatingDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductRatingService);
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
