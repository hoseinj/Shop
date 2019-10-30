import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { OrderTotalDeleteDialogComponent } from 'app/entities/order-total/order-total-delete-dialog.component';
import { OrderTotalService } from 'app/entities/order-total/order-total.service';

describe('Component Tests', () => {
  describe('OrderTotal Management Delete Component', () => {
    let comp: OrderTotalDeleteDialogComponent;
    let fixture: ComponentFixture<OrderTotalDeleteDialogComponent>;
    let service: OrderTotalService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [OrderTotalDeleteDialogComponent]
      })
        .overrideTemplate(OrderTotalDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderTotalDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderTotalService);
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
