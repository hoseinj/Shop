import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { CustomerWishlistDeleteDialogComponent } from 'app/entities/customer-wishlist/customer-wishlist-delete-dialog.component';
import { CustomerWishlistService } from 'app/entities/customer-wishlist/customer-wishlist.service';

describe('Component Tests', () => {
  describe('CustomerWishlist Management Delete Component', () => {
    let comp: CustomerWishlistDeleteDialogComponent;
    let fixture: ComponentFixture<CustomerWishlistDeleteDialogComponent>;
    let service: CustomerWishlistService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerWishlistDeleteDialogComponent]
      })
        .overrideTemplate(CustomerWishlistDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerWishlistDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerWishlistService);
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
