import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Foobar } from './foobar.model';
import { FoobarService } from './foobar.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-foobar',
    templateUrl: './foobar.component.html'
})
export class FoobarComponent implements OnInit, OnDestroy {
foobars: Foobar[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private foobarService: FoobarService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.foobarService.query().subscribe(
            (res: HttpResponse<Foobar[]>) => {
                this.foobars = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInFoobars();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Foobar) {
        return item.id;
    }
    registerChangeInFoobars() {
        this.eventSubscriber = this.eventManager.subscribe('foobarListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
