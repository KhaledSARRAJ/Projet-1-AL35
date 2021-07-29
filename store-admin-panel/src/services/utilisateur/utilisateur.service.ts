import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Utilisateur} from "../../models/utilisateur/utilisateur";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private serviceUrl = 'http://localhost:8085/administrateur/';
  constructor(private http: HttpClient) { }

  getUtilisateurs(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.serviceUrl + 'users');
  }
}
