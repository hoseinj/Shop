import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { CustomerIpDeleteDialogComponent } from 'app/entities/customer-ip/customer-ip-delete-dialog.component';
import { CustomerIpService } from 'app/entities/customer-ip/customer-ip.service';

describe('Component Tests', () => {
  describe('CustomerIp Management Delete Component', () => {
    let comp: CustomerIpDeleteDialogComponent;
    let fixture: ComponentFixture<CustomerIpDeleteDialogComponent>;
    let service: CustomerIpService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerIpDeleteDialogComponent]
      })
        .overrideTemplate(CustomerIpDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerIpDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerIpService);
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
