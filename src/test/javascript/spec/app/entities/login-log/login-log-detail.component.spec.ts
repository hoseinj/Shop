import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { LoginLogDetailComponent } from 'app/entities/login-log/login-log-detail.component';
import { LoginLog } from 'app/shared/model/login-log.model';

describe('Component Tests', () => {
  describe('LoginLog Management Detail Component', () => {
    let comp: LoginLogDetailComponent;
    let fixture: ComponentFixture<LoginLogDetailComponent>;
    const route = ({ data: of({ loginLog: new LoginLog(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [LoginLogDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LoginLogDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LoginLogDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.loginLog).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
