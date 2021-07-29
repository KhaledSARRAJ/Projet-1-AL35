import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Annonces} from "../../models/annonces/annonces";

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {

  private serviceUrl = 'http://localhost:8085/administrateur/';

  constructor(private http: HttpClient) { }

  getAnnonces(): Observable<Annonces[]> {
    return this.http.get<Annonces[]>(this.serviceUrl + 'annonces');
  }

  deleteAnnonce(id: number): Observable<any> {
    return this.http.delete<any>(this.serviceUrl + 'deleteAnnonce/' + id);
  }
}
