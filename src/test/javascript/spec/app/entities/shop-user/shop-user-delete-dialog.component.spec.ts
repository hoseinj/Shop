import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { ShopUserDeleteDialogComponent } from 'app/entities/shop-user/shop-user-delete-dialog.component';
import { ShopUserService } from 'app/entities/shop-user/shop-user.service';

describe('Component Tests', () => {
  describe('ShopUser Management Delete Component', () => {
    let comp: ShopUserDeleteDialogComponent;
    let fixture: ComponentFixture<ShopUserDeleteDialogComponent>;
    let service: ShopUserService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ShopUserDeleteDialogComponent]
      })
        .overrideTemplate(ShopUserDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShopUserDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShopUserService);
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
