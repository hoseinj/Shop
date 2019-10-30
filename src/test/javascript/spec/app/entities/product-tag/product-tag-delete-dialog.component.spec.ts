import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ShopTestModule } from '../../../test.module';
import { ProductTagDeleteDialogComponent } from 'app/entities/product-tag/product-tag-delete-dialog.component';
import { ProductTagService } from 'app/entities/product-tag/product-tag.service';

describe('Component Tests', () => {
  describe('ProductTag Management Delete Component', () => {
    let comp: ProductTagDeleteDialogComponent;
    let fixture: ComponentFixture<ProductTagDeleteDialogComponent>;
    let service: ProductTagService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductTagDeleteDialogComponent]
      })
        .overrideTemplate(ProductTagDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductTagDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductTagService);
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
