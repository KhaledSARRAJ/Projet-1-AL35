import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PublicationTableItem} from "../../app/publication-table/publication-table-datasource";
import {Signalement} from "../../models/signalement/signalement";

@Injectable({
  providedIn: 'root'
})
export class SignalementService {
  private serviceUrl = 'http://localhost:8081/aec-api-rest/signalements/';

  constructor(private http: HttpClient) {
  }

  getSignalement(): Observable<Signalement[]> {
    return this.http.get<Signalement[]>(this.serviceUrl + 'simpleListe');
  }

  deleteSignalement(id: number): Observable<any> {
    return this.http.delete<any>(this.serviceUrl + id);
  }
}
