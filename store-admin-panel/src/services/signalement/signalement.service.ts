import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PublicationTableItem} from "../../app/publication-table/publication-table-datasource";
import {Signalement} from "../../models/signalement/signalement";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SignalementService {
  private serviceUrl = environment.signalementUrl;
  constructor(private http: HttpClient) {
  }
  getSignalement(): Observable<Signalement[]> {
    return this.http.get<Signalement[]>(this.serviceUrl + 'simpleListe');
  }
  deleteSignalement(id: number): Observable<any> {
    return this.http.delete<any>(this.serviceUrl + id);
  }
}
