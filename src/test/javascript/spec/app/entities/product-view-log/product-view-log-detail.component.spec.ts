import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ProductViewLogDetailComponent } from 'app/entities/product-view-log/product-view-log-detail.component';
import { ProductViewLog } from 'app/shared/model/product-view-log.model';

describe('Component Tests', () => {
  describe('ProductViewLog Management Detail Component', () => {
    let comp: ProductViewLogDetailComponent;
    let fixture: ComponentFixture<ProductViewLogDetailComponent>;
    const route = ({ data: of({ productViewLog: new ProductViewLog(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductViewLogDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductViewLogDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductViewLogDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productViewLog).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
