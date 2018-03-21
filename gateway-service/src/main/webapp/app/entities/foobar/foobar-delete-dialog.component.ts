import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Foobar } from './foobar.model';
import { FoobarPopupService } from './foobar-popup.service';
import { FoobarService } from './foobar.service';

@Component({
    selector: 'jhi-foobar-delete-dialog',
    templateUrl: './foobar-delete-dialog.component.html'
})
export class FoobarDeleteDialogComponent {

    foobar: Foobar;

    constructor(
        private foobarService: FoobarService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.foobarService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'foobarListModification',
                content: 'Deleted an foobar'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-foobar-delete-popup',
    template: ''
})
export class FoobarDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private foobarPopupService: FoobarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.foobarPopupService
                .open(FoobarDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
