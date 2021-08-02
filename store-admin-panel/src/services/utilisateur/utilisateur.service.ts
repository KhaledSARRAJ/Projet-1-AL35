import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Utilisateur} from "../../models/utilisateur/utilisateur";
import {HttpClient} from "@angular/common/http";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private serviceUrl = environment.serviceUrl;
  constructor(private http: HttpClient) { }

  getUtilisateurs(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.serviceUrl + 'users');
  }
}
