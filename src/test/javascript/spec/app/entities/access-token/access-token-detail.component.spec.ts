import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { AccessTokenDetailComponent } from 'app/entities/access-token/access-token-detail.component';
import { AccessToken } from 'app/shared/model/access-token.model';

describe('Component Tests', () => {
  describe('AccessToken Management Detail Component', () => {
    let comp: AccessTokenDetailComponent;
    let fixture: ComponentFixture<AccessTokenDetailComponent>;
    const route = ({ data: of({ accessToken: new AccessToken(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [AccessTokenDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AccessTokenDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AccessTokenDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.accessToken).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
