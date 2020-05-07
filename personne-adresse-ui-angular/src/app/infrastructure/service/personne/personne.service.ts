import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { toArray, map, catchError, finalize} from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Personne } from 'src/app/domain/personne';
import { v4 } from 'uuid';

@Injectable({
  providedIn: 'root'
})
export class PersonneService {
  httpHeaderFwk: HttpHeaders= new HttpHeaders(
    {
      'Content-Type': 'application/json',
      'pe-id-correlation': v4(),
      'pe-id-utilisateur': 'IJFA3650',
      'pe-nom-application': 'personne-adresse-ui'
    }
  );
  urlPersonne: string = environment.urlPersonneApi;
  
  constructor(private readonly http: HttpClient) {}

  getPersonnes(): Observable<Personne[]> {
    return this.http
      .get(`${this.urlPersonne}/personnes`, { headers: this.httpHeaderFwk })
      .pipe( 
        map((personne: Personne[]) => personne)
      );
  }
}
