import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { GeoZoneDetailComponent } from 'app/entities/geo-zone/geo-zone-detail.component';
import { GeoZone } from 'app/shared/model/geo-zone.model';

describe('Component Tests', () => {
  describe('GeoZone Management Detail Component', () => {
    let comp: GeoZoneDetailComponent;
    let fixture: ComponentFixture<GeoZoneDetailComponent>;
    const route = ({ data: of({ geoZone: new GeoZone(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [GeoZoneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GeoZoneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoZoneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoZone).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
