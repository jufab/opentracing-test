import { Personne } from 'src/app/domain/personne';
import { DataSource, CollectionViewer } from '@angular/cdk/collections';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { PersonneService } from 'src/app/infrastructure/service/personne/personne.service';
import { catchError, finalize } from 'rxjs/operators';


export class PersonnesDataSource implements DataSource<Personne> {

    private personnesSubject = new BehaviorSubject<Personne[]>([]);
    private loadingSubject = new BehaviorSubject<boolean>(false);
    public loading$ = this.loadingSubject.asObservable();

    constructor(private personneService: PersonneService) { }

    connect(collectionViewer: CollectionViewer): Observable<Personne[]> {
        return this.personnesSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.personnesSubject.complete();
        this.loadingSubject.complete();
    }

    loadPersonnes() {
        this.loadingSubject.next(true);
        this.personneService.getPersonnes()
        .pipe(
            catchError(() => of([])),
            finalize(() => this.loadingSubject.next(false)
        )).subscribe(personnes => this.personnesSubject.next(personnes));
    }
}