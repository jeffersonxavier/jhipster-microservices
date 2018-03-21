import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewayServiceSharedModule } from '../../shared';
import {
    FoobarService,
    FoobarPopupService,
    FoobarComponent,
    FoobarDetailComponent,
    FoobarDialogComponent,
    FoobarPopupComponent,
    FoobarDeletePopupComponent,
    FoobarDeleteDialogComponent,
    foobarRoute,
    foobarPopupRoute,
} from './';

const ENTITY_STATES = [
    ...foobarRoute,
    ...foobarPopupRoute,
];

@NgModule({
    imports: [
        GatewayServiceSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        FoobarComponent,
        FoobarDetailComponent,
        FoobarDialogComponent,
        FoobarDeleteDialogComponent,
        FoobarPopupComponent,
        FoobarDeletePopupComponent,
    ],
    entryComponents: [
        FoobarComponent,
        FoobarDialogComponent,
        FoobarPopupComponent,
        FoobarDeleteDialogComponent,
        FoobarDeletePopupComponent,
    ],
    providers: [
        FoobarService,
        FoobarPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayServiceFoobarModule {}
