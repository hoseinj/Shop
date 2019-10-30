import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { BannerGroupDeleteDialogComponent } from 'app/entities/banner-group/banner-group-delete-dialog.component';
import { BannerGroupService } from 'app/entities/banner-group/banner-group.service';

describe('Component Tests', () => {
  describe('BannerGroup Management Delete Component', () => {
    let comp: BannerGroupDeleteDialogComponent;
    let fixture: ComponentFixture<BannerGroupDeleteDialogComponent>;
    let service: BannerGroupService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [BannerGroupDeleteDialogComponent]
      })
        .overrideTemplate(BannerGroupDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BannerGroupDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BannerGroupService);
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
