import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { FoobarComponent } from './foobar.component';
import { FoobarDetailComponent } from './foobar-detail.component';
import { FoobarPopupComponent } from './foobar-dialog.component';
import { FoobarDeletePopupComponent } from './foobar-delete-dialog.component';

export const foobarRoute: Routes = [
    {
        path: 'foobar',
        component: FoobarComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foobars'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'foobar/:id',
        component: FoobarDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foobars'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const foobarPopupRoute: Routes = [
    {
        path: 'foobar-new',
        component: FoobarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foobars'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'foobar/:id/edit',
        component: FoobarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foobars'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'foobar/:id/delete',
        component: FoobarDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Foobars'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
