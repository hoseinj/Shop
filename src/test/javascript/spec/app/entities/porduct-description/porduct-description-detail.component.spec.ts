import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { PorductDescriptionDetailComponent } from 'app/entities/porduct-description/porduct-description-detail.component';
import { PorductDescription } from 'app/shared/model/porduct-description.model';

describe('Component Tests', () => {
  describe('PorductDescription Management Detail Component', () => {
    let comp: PorductDescriptionDetailComponent;
    let fixture: ComponentFixture<PorductDescriptionDetailComponent>;
    const route = ({ data: of({ porductDescription: new PorductDescription(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [PorductDescriptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PorductDescriptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PorductDescriptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.porductDescription).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
