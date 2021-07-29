import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Annonces} from './Annonces'

@Injectable({
  providedIn: 'root'
})

export class AnnoncesService {
  private annoncesUrl = 'http://localhost:8085/administrateur/annonces';

  constructor(private http: HttpClient) { }

  getAnnonces(): Observable<Annonces[]>{
    return this.http.get<Annonces[]>(this.annoncesUrl).pipe(catchError(this.handleError));
  }

  private handleError(err: HttpErrorResponse){
    return throwError(`An error occurred: ${err}`);
  }
}
