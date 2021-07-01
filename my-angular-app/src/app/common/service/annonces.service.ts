import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { AnnonceRequest } from "../data/AnnonceRequest";
import { AnnonceResponse } from "../data/AnnonceResponse";
import { AnnonceSimple } from "../data/annonceSimple";


@Injectable({
    providedIn: 'root'
})
export class AnnoncesService {

    private _headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    // private _apiBaseUrl = "http://localhost:8085/aec-api-rest/annonces";
    private _apiBaseUrl = "./aec-api-rest/annonces";

    constructor(private _http: HttpClient) { }

    public getAllAnnoncesSimple$(): Observable<AnnonceSimple[]> {
        let url = this._apiBaseUrl + "/simpleListe";
        console.log("url = " + url);
        return this._http.get<AnnonceSimple[]>(url);
    }

    public getAnnonceById$(annonceId: number): Observable<AnnonceResponse> {
        let url = this._apiBaseUrl + "/infos/" + annonceId;
        console.log("url = " + url);
        return this._http.get<AnnonceResponse>(url);
    }

    public getAnnonceByUti$(utiId: number): Observable<AnnonceSimple[]> {
        let url = this._apiBaseUrl + "/mesAnnonces/" + utiId;
        console.log("url = " + url);
        return this._http.get<AnnonceSimple[]>(url);
    }

    public deleteAnnonce$(annonceId: number): Observable<any> {
        let url = this._apiBaseUrl + "/" + annonceId;
        console.log("url = " + url);
        return this._http.delete(url);
    }

    public creatAnnonce$(a: AnnonceRequest): Observable<AnnonceResponse> {
        let url = this._apiBaseUrl + "/";
        console.log("PostUrl=" + url);
        return this._http.post<AnnonceResponse>(url, a, { headers: this._headers });
    }

    public majAnnonce$(a: AnnonceRequest): Observable<AnnonceResponse> {
        let url = this._apiBaseUrl + "/" + a.id;
        console.log("PostUrl=" + url);
        return this._http.put<AnnonceResponse>(url, a, { headers: this._headers });
    }
}

