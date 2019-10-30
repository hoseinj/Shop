import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { OrderOptionDeleteDialogComponent } from 'app/entities/order-option/order-option-delete-dialog.component';
import { OrderOptionService } from 'app/entities/order-option/order-option.service';

describe('Component Tests', () => {
  describe('OrderOption Management Delete Component', () => {
    let comp: OrderOptionDeleteDialogComponent;
    let fixture: ComponentFixture<OrderOptionDeleteDialogComponent>;
    let service: OrderOptionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [OrderOptionDeleteDialogComponent]
      })
        .overrideTemplate(OrderOptionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrderOptionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrderOptionService);
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
