import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ProductTagDetailComponent } from 'app/entities/product-tag/product-tag-detail.component';
import { ProductTag } from 'app/shared/model/product-tag.model';

describe('Component Tests', () => {
  describe('ProductTag Management Detail Component', () => {
    let comp: ProductTagDetailComponent;
    let fixture: ComponentFixture<ProductTagDetailComponent>;
    const route = ({ data: of({ productTag: new ProductTag(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ProductTagDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProductTagDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductTagDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productTag).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
