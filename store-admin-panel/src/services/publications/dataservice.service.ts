import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PublicationTableItem} from "../../app/publication-table/publication-table-datasource";

@Injectable({
  providedIn: 'root'
})
export class DataserviceService {
  private serviceUrl = 'http://localhost:8085/administrateur/';

  constructor(private http: HttpClient) {
  }

  getPublicationss(): Observable<PublicationTableItem[]> {
    return this.http.get<PublicationTableItem[]>(this.serviceUrl + 'publications');
  }

  deletePublication(id: number): Observable<any> {
    return this.http.delete<any>(this.serviceUrl + 'deletePublications/' + id);
  }
}
