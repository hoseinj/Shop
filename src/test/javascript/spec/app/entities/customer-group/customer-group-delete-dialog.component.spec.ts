import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { CustomerGroupDeleteDialogComponent } from 'app/entities/customer-group/customer-group-delete-dialog.component';
import { CustomerGroupService } from 'app/entities/customer-group/customer-group.service';

describe('Component Tests', () => {
  describe('CustomerGroup Management Delete Component', () => {
    let comp: CustomerGroupDeleteDialogComponent;
    let fixture: ComponentFixture<CustomerGroupDeleteDialogComponent>;
    let service: CustomerGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerGroupDeleteDialogComponent]
      })
        .overrideTemplate(CustomerGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerGroupService);
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
