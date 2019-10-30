import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { BannerImageDescriptionDetailComponent } from 'app/entities/banner-image-description/banner-image-description-detail.component';
import { BannerImageDescription } from 'app/shared/model/banner-image-description.model';

describe('Component Tests', () => {
  describe('BannerImageDescription Management Detail Component', () => {
    let comp: BannerImageDescriptionDetailComponent;
    let fixture: ComponentFixture<BannerImageDescriptionDetailComponent>;
    const route = ({ data: of({ bannerImageDescription: new BannerImageDescription(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [BannerImageDescriptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BannerImageDescriptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BannerImageDescriptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bannerImageDescription).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
