import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Annonces} from './Annonces'
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AnnoncesService {
  private annoncesUrl = environment.annoncesUrl;

  constructor(private http: HttpClient) { }

  getAnnonces(): Observable<Annonces[]>{
    return this.http.get<Annonces[]>(this.annoncesUrl).pipe(catchError(this.handleError));
  }

  private handleError(err: HttpErrorResponse){
    return throwError(`An error occurred: ${err}`);
  }
}
