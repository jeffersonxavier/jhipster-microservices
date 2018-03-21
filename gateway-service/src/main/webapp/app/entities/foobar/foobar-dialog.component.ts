import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Foobar } from './foobar.model';
import { FoobarPopupService } from './foobar-popup.service';
import { FoobarService } from './foobar.service';

@Component({
    selector: 'jhi-foobar-dialog',
    templateUrl: './foobar-dialog.component.html'
})
export class FoobarDialogComponent implements OnInit {

    foobar: Foobar;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private foobarService: FoobarService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.foobar.id !== undefined) {
            this.subscribeToSaveResponse(
                this.foobarService.update(this.foobar));
        } else {
            this.subscribeToSaveResponse(
                this.foobarService.create(this.foobar));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Foobar>>) {
        result.subscribe((res: HttpResponse<Foobar>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Foobar) {
        this.eventManager.broadcast({ name: 'foobarListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-foobar-popup',
    template: ''
})
export class FoobarPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private foobarPopupService: FoobarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.foobarPopupService
                    .open(FoobarDialogComponent as Component, params['id']);
            } else {
                this.foobarPopupService
                    .open(FoobarDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
