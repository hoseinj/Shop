import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { BannerImageDescriptionDeleteDialogComponent } from 'app/entities/banner-image-description/banner-image-description-delete-dialog.component';
import { BannerImageDescriptionService } from 'app/entities/banner-image-description/banner-image-description.service';

describe('Component Tests', () => {
  describe('BannerImageDescription Management Delete Component', () => {
    let comp: BannerImageDescriptionDeleteDialogComponent;
    let fixture: ComponentFixture<BannerImageDescriptionDeleteDialogComponent>;
    let service: BannerImageDescriptionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [BannerImageDescriptionDeleteDialogComponent]
      })
        .overrideTemplate(BannerImageDescriptionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BannerImageDescriptionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BannerImageDescriptionService);
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
