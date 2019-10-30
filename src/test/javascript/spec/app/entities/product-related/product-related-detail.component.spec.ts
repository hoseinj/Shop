import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ProductRelatedDetailComponent } from 'app/entities/product-related/product-related-detail.component';
import { ProductRelated } from 'app/shared/model/product-related.model';

describe('Component Tests', () => {
  describe('ProductRelated Management Detail Component', () => {
    let comp: ProductRelatedDetailComponent;
    let fixture: ComponentFixture<ProductRelatedDetailComponent>;
    const route = ({ data: of({ productRelated: new ProductRelated(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductRelatedDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductRelatedDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductRelatedDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productRelated).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
