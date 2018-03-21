import { BaseEntity } from './../../shared';

export class Foobar implements BaseEntity {
    constructor(
        public id?: number,
        public foobarName?: string,
    ) {
    }
}
