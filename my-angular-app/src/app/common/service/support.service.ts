import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { SupportRequest } from "../data/SupportRequest";
import { SupportResponse } from "../data/SupportResponse";

@Injectable({
    providedIn: 'root'
})
export class SupportService {
    private _headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    private _apiBaseUrl = "./aec-api-rest/supports";

    constructor(private _http: HttpClient) { }

    public creatSupport$(s: SupportRequest): Observable<SupportResponse> {
        let url = this._apiBaseUrl + "/";
        console.log("PostUrl=" + url);
        return this._http.post<SupportResponse>(url, s, { headers: this._headers });
    }

    public updateSupport$(s: SupportRequest): Observable<SupportResponse> {
        let url = this._apiBaseUrl + "/" + s.id;
        console.log("PostUrl=" + url);
        return this._http.put<SupportResponse>(url, s, { headers: this._headers });
    }

    public deleteSupport$(supportId: number): Observable<any> {
        let url = this._apiBaseUrl + "/" + supportId;
        console.log("url = " + url);
        return this._http.delete(url);
    }

    public getSupportById$(supportId: number): Observable<SupportResponse> {
        let url = this._apiBaseUrl + "/" + supportId;
        console.log("PostUrl=" + url);
        return this._http.get<SupportResponse>(url);
    }
    
}
