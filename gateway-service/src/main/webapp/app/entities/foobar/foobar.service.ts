import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Foobar } from './foobar.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Foobar>;

@Injectable()
export class FoobarService {

    private resourceUrl =  SERVER_API_URL + 'api/foobars';

    constructor(private http: HttpClient) { }

    create(foobar: Foobar): Observable<EntityResponseType> {
        const copy = this.convert(foobar);
        return this.http.post<Foobar>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(foobar: Foobar): Observable<EntityResponseType> {
        const copy = this.convert(foobar);
        return this.http.put<Foobar>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Foobar>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Foobar[]>> {
        const options = createRequestOption(req);
        return this.http.get<Foobar[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Foobar[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Foobar = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Foobar[]>): HttpResponse<Foobar[]> {
        const jsonResponse: Foobar[] = res.body;
        const body: Foobar[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Foobar.
     */
    private convertItemFromServer(foobar: Foobar): Foobar {
        const copy: Foobar = Object.assign({}, foobar);
        return copy;
    }

    /**
     * Convert a Foobar to a JSON which can be sent to the server.
     */
    private convert(foobar: Foobar): Foobar {
        const copy: Foobar = Object.assign({}, foobar);
        return copy;
    }
}
