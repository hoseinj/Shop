import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { CustomerTransactionDeleteDialogComponent } from 'app/entities/customer-transaction/customer-transaction-delete-dialog.component';
import { CustomerTransactionService } from 'app/entities/customer-transaction/customer-transaction.service';

describe('Component Tests', () => {
  describe('CustomerTransaction Management Delete Component', () => {
    let comp: CustomerTransactionDeleteDialogComponent;
    let fixture: ComponentFixture<CustomerTransactionDeleteDialogComponent>;
    let service: CustomerTransactionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerTransactionDeleteDialogComponent]
      })
        .overrideTemplate(CustomerTransactionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerTransactionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerTransactionService);
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
