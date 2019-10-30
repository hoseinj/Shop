import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { ShopUserDetailComponent } from 'app/entities/shop-user/shop-user-detail.component';
import { ShopUser } from 'app/shared/model/shop-user.model';

describe('Component Tests', () => {
  describe('ShopUser Management Detail Component', () => {
    let comp: ShopUserDetailComponent;
    let fixture: ComponentFixture<ShopUserDetailComponent>;
    const route = ({ data: of({ shopUser: new ShopUser(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [ShopUserDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ShopUserDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShopUserDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.shopUser).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
