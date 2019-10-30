import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CustomerWishlistDetailComponent } from 'app/entities/customer-wishlist/customer-wishlist-detail.component';
import { CustomerWishlist } from 'app/shared/model/customer-wishlist.model';

describe('Component Tests', () => {
  describe('CustomerWishlist Management Detail Component', () => {
    let comp: CustomerWishlistDetailComponent;
    let fixture: ComponentFixture<CustomerWishlistDetailComponent>;
    const route = ({ data: of({ customerWishlist: new CustomerWishlist(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerWishlistDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustomerWishlistDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerWishlistDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.customerWishlist).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
