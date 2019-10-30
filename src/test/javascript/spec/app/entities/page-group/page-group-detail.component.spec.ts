import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { PageGroupDetailComponent } from 'app/entities/page-group/page-group-detail.component';
import { PageGroup } from 'app/shared/model/page-group.model';

describe('Component Tests', () => {
  describe('PageGroup Management Detail Component', () => {
    let comp: PageGroupDetailComponent;
    let fixture: ComponentFixture<PageGroupDetailComponent>;
    const route = ({ data: of({ pageGroup: new PageGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [PageGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PageGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PageGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pageGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
