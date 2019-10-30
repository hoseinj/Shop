import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ShopPageDetailComponent } from 'app/entities/shop-page/shop-page-detail.component';
import { ShopPage } from 'app/shared/model/shop-page.model';

describe('Component Tests', () => {
  describe('ShopPage Management Detail Component', () => {
    let comp: ShopPageDetailComponent;
    let fixture: ComponentFixture<ShopPageDetailComponent>;
    const route = ({ data: of({ shopPage: new ShopPage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ShopPageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ShopPageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShopPageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.shopPage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
