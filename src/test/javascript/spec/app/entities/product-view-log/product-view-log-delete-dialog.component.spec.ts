import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { ProductViewLogDeleteDialogComponent } from 'app/entities/product-view-log/product-view-log-delete-dialog.component';
import { ProductViewLogService } from 'app/entities/product-view-log/product-view-log.service';

describe('Component Tests', () => {
  describe('ProductViewLog Management Delete Component', () => {
    let comp: ProductViewLogDeleteDialogComponent;
    let fixture: ComponentFixture<ProductViewLogDeleteDialogComponent>;
    let service: ProductViewLogService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductViewLogDeleteDialogComponent]
      })
        .overrideTemplate(ProductViewLogDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductViewLogDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductViewLogService);
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
