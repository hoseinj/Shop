import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { CategoryDescriptionDeleteDialogComponent } from 'app/entities/category-description/category-description-delete-dialog.component';
import { CategoryDescriptionService } from 'app/entities/category-description/category-description.service';

describe('Component Tests', () => {
  describe('CategoryDescription Management Delete Component', () => {
    let comp: CategoryDescriptionDeleteDialogComponent;
    let fixture: ComponentFixture<CategoryDescriptionDeleteDialogComponent>;
    let service: CategoryDescriptionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CategoryDescriptionDeleteDialogComponent]
      })
        .overrideTemplate(CategoryDescriptionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoryDescriptionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategoryDescriptionService);
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
