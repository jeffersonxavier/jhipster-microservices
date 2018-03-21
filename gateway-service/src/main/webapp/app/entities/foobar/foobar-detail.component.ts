import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Foobar } from './foobar.model';
import { FoobarService } from './foobar.service';

@Component({
    selector: 'jhi-foobar-detail',
    templateUrl: './foobar-detail.component.html'
})
export class FoobarDetailComponent implements OnInit, OnDestroy {

    foobar: Foobar;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private foobarService: FoobarService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFoobars();
    }

    load(id) {
        this.foobarService.find(id)
            .subscribe((foobarResponse: HttpResponse<Foobar>) => {
                this.foobar = foobarResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFoobars() {
        this.eventSubscriber = this.eventManager.subscribe(
            'foobarListModification',
            (response) => this.load(this.foobar.id)
        );
    }
}
