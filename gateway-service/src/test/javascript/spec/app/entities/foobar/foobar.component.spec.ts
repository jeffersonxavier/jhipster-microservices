/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewayServiceTestModule } from '../../../test.module';
import { FoobarComponent } from '../../../../../../main/webapp/app/entities/foobar/foobar.component';
import { FoobarService } from '../../../../../../main/webapp/app/entities/foobar/foobar.service';
import { Foobar } from '../../../../../../main/webapp/app/entities/foobar/foobar.model';

describe('Component Tests', () => {

    describe('Foobar Management Component', () => {
        let comp: FoobarComponent;
        let fixture: ComponentFixture<FoobarComponent>;
        let service: FoobarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [GatewayServiceTestModule],
                declarations: [FoobarComponent],
                providers: [
                    FoobarService
                ]
            })
            .overrideTemplate(FoobarComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FoobarComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FoobarService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Foobar(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.foobars[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
