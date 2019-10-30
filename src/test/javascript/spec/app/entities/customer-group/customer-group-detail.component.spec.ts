import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CustomerGroupDetailComponent } from 'app/entities/customer-group/customer-group-detail.component';
import { CustomerGroup } from 'app/shared/model/customer-group.model';

describe('Component Tests', () => {
  describe('CustomerGroup Management Detail Component', () => {
    let comp: CustomerGroupDetailComponent;
    let fixture: ComponentFixture<CustomerGroupDetailComponent>;
    const route = ({ data: of({ customerGroup: new CustomerGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustomerGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.customerGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
