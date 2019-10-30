import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { MigrationsDetailComponent } from 'app/entities/migrations/migrations-detail.component';
import { Migrations } from 'app/shared/model/migrations.model';

describe('Component Tests', () => {
  describe('Migrations Management Detail Component', () => {
    let comp: MigrationsDetailComponent;
    let fixture: ComponentFixture<MigrationsDetailComponent>;
    const route = ({ data: of({ migrations: new Migrations(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [MigrationsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MigrationsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MigrationsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.migrations).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
