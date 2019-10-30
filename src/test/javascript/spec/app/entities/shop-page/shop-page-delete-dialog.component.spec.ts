import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { ShopPageDeleteDialogComponent } from 'app/entities/shop-page/shop-page-delete-dialog.component';
import { ShopPageService } from 'app/entities/shop-page/shop-page.service';

describe('Component Tests', () => {
  describe('ShopPage Management Delete Component', () => {
    let comp: ShopPageDeleteDialogComponent;
    let fixture: ComponentFixture<ShopPageDeleteDialogComponent>;
    let service: ShopPageService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ShopPageDeleteDialogComponent]
      })
        .overrideTemplate(ShopPageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShopPageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShopPageService);
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
