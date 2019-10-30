import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { CategoryPathDeleteDialogComponent } from 'app/entities/category-path/category-path-delete-dialog.component';
import { CategoryPathService } from 'app/entities/category-path/category-path.service';

describe('Component Tests', () => {
  describe('CategoryPath Management Delete Component', () => {
    let comp: CategoryPathDeleteDialogComponent;
    let fixture: ComponentFixture<CategoryPathDeleteDialogComponent>;
    let service: CategoryPathService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CategoryPathDeleteDialogComponent]
      })
        .overrideTemplate(CategoryPathDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoryPathDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoryPathService);
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
