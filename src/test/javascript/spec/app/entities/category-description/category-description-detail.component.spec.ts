import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CategoryDescriptionDetailComponent } from 'app/entities/category-description/category-description-detail.component';
import { CategoryDescription } from 'app/shared/model/category-description.model';

describe('Component Tests', () => {
  describe('CategoryDescription Management Detail Component', () => {
    let comp: CategoryDescriptionDetailComponent;
    let fixture: ComponentFixture<CategoryDescriptionDetailComponent>;
    const route = ({ data: of({ categoryDescription: new CategoryDescription(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CategoryDescriptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CategoryDescriptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategoryDescriptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categoryDescription).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
