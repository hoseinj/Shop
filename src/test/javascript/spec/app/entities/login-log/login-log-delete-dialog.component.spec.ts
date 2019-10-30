import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { LoginLogDeleteDialogComponent } from 'app/entities/login-log/login-log-delete-dialog.component';
import { LoginLogService } from 'app/entities/login-log/login-log.service';

describe('Component Tests', () => {
  describe('LoginLog Management Delete Component', () => {
    let comp: LoginLogDeleteDialogComponent;
    let fixture: ComponentFixture<LoginLogDeleteDialogComponent>;
    let service: LoginLogService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [LoginLogDeleteDialogComponent]
      })
        .overrideTemplate(LoginLogDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LoginLogDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LoginLogService);
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
