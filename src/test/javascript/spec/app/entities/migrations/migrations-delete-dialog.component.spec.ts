import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { MigrationsDeleteDialogComponent } from 'app/entities/migrations/migrations-delete-dialog.component';
import { MigrationsService } from 'app/entities/migrations/migrations.service';

describe('Component Tests', () => {
  describe('Migrations Management Delete Component', () => {
    let comp: MigrationsDeleteDialogComponent;
    let fixture: ComponentFixture<MigrationsDeleteDialogComponent>;
    let service: MigrationsService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [MigrationsDeleteDialogComponent]
      })
        .overrideTemplate(MigrationsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MigrationsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MigrationsService);
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
