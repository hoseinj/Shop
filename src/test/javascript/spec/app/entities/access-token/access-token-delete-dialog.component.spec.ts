import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { AccessTokenDeleteDialogComponent } from 'app/entities/access-token/access-token-delete-dialog.component';
import { AccessTokenService } from 'app/entities/access-token/access-token.service';

describe('Component Tests', () => {
  describe('AccessToken Management Delete Component', () => {
    let comp: AccessTokenDeleteDialogComponent;
    let fixture: ComponentFixture<AccessTokenDeleteDialogComponent>;
    let service: AccessTokenService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [AccessTokenDeleteDialogComponent]
      })
        .overrideTemplate(AccessTokenDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AccessTokenDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AccessTokenService);
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
