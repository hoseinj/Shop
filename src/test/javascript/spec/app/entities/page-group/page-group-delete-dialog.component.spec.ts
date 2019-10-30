import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { PageGroupDeleteDialogComponent } from 'app/entities/page-group/page-group-delete-dialog.component';
import { PageGroupService } from 'app/entities/page-group/page-group.service';

describe('Component Tests', () => {
  describe('PageGroup Management Delete Component', () => {
    let comp: PageGroupDeleteDialogComponent;
    let fixture: ComponentFixture<PageGroupDeleteDialogComponent>;
    let service: PageGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [PageGroupDeleteDialogComponent]
      })
        .overrideTemplate(PageGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PageGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PageGroupService);
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
