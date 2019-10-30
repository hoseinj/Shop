import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ProductRatingDetailComponent } from 'app/entities/product-rating/product-rating-detail.component';
import { ProductRating } from 'app/shared/model/product-rating.model';

describe('Component Tests', () => {
  describe('ProductRating Management Detail Component', () => {
    let comp: ProductRatingDetailComponent;
    let fixture: ComponentFixture<ProductRatingDetailComponent>;
    const route = ({ data: of({ productRating: new ProductRating(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductRatingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductRatingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductRatingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productRating).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
