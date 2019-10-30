import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CategoryPathDetailComponent } from 'app/entities/category-path/category-path-detail.component';
import { CategoryPath } from 'app/shared/model/category-path.model';

describe('Component Tests', () => {
  describe('CategoryPath Management Detail Component', () => {
    let comp: CategoryPathDetailComponent;
    let fixture: ComponentFixture<CategoryPathDetailComponent>;
    const route = ({ data: of({ categoryPath: new CategoryPath(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CategoryPathDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CategoryPathDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoryPathDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categoryPath).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
