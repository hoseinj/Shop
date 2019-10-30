import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { BannerGroupDetailComponent } from 'app/entities/banner-group/banner-group-detail.component';
import { BannerGroup } from 'app/shared/model/banner-group.model';

describe('Component Tests', () => {
  describe('BannerGroup Management Detail Component', () => {
    let comp: BannerGroupDetailComponent;
    let fixture: ComponentFixture<BannerGroupDetailComponent>;
    const route = ({ data: of({ bannerGroup: new BannerGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [BannerGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BannerGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BannerGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bannerGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
