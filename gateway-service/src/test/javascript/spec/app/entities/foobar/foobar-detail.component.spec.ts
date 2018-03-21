/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { GatewayServiceTestModule } from '../../../test.module';
import { FoobarDetailComponent } from '../../../../../../main/webapp/app/entities/foobar/foobar-detail.component';
import { FoobarService } from '../../../../../../main/webapp/app/entities/foobar/foobar.service';
import { Foobar } from '../../../../../../main/webapp/app/entities/foobar/foobar.model';

describe('Component Tests', () => {

    describe('Foobar Management Detail Component', () => {
        let comp: FoobarDetailComponent;
        let fixture: ComponentFixture<FoobarDetailComponent>;
        let service: FoobarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GatewayServiceTestModule],
                declarations: [FoobarDetailComponent],
                providers: [
                    FoobarService
                ]
            })
            .overrideTemplate(FoobarDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FoobarDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FoobarService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Foobar(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.foobar).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
