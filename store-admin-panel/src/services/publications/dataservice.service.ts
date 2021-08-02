import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PublicationTableItem} from "../../app/publication-table/publication-table-datasource";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DataserviceService {
  private serviceUrl = environment.serviceUrl;
  idPublicationDetails: number;
  constructor(private http: HttpClient) {
  }

  getPublicationss(): Observable<PublicationTableItem[]> {
    return this.http.get<PublicationTableItem[]>(this.serviceUrl + 'publications');
  }

  deletePublication(id: number): Observable<any> {
    return this.http.delete<any>(this.serviceUrl + 'deletePublications/' + id);
  }
  getPublication(id: number): Observable<any> {
    return this.http.get<any>(this.serviceUrl + 'publication/' + id);
  }
}
