import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShopTestModule } from '../../../test.module';
import { CustomerIpDetailComponent } from 'app/entities/customer-ip/customer-ip-detail.component';
import { CustomerIp } from 'app/shared/model/customer-ip.model';

describe('Component Tests', () => {
  describe('CustomerIp Management Detail Component', () => {
    let comp: CustomerIpDetailComponent;
    let fixture: ComponentFixture<CustomerIpDetailComponent>;
    const route = ({ data: of({ customerIp: new CustomerIp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ShopTestModule],
        declarations: [CustomerIpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CustomerIpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerIpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.customerIp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
