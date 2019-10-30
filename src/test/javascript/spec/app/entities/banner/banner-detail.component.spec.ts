import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { BannerDetailComponent } from 'app/entities/banner/banner-detail.component';
import { Banner } from 'app/shared/model/banner.model';

describe('Component Tests', () => {
  describe('Banner Management Detail Component', () => {
    let comp: BannerDetailComponent;
    let fixture: ComponentFixture<BannerDetailComponent>;
    const route = ({ data: of({ banner: new Banner(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [BannerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BannerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BannerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.banner).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
