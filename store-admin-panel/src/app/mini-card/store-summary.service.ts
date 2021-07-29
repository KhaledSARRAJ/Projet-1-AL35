import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { UtilisateurService } from 'src/services/utilisateur/utilisateur.service';
import {StoreSummary} from "./store-summary";
import {UtilsServiceService} from "../../services/utils/utils-service.service";

@Injectable({
  providedIn: 'root'
})
export class StoreSummaryService {

  getStoreSummary(countUsers, countPub, countAnnonces, countSignalement): Observable<StoreSummary[]> {
    return of([
      { title: "Nombre des Publications", value: countPub, isIncrease: false, color: "primary", percentValue: "0.5383", icon: "payments", isCurrency: false },
      { title: "Nombre des Signalements", value: countSignalement, isIncrease: false, color: "warn", percentValue: "0.2544", icon: "block", isCurrency: false },
      { title: "Nombre des Annonces", value: countAnnonces, isIncrease: true, color: "accent", percentValue: "0.4565", icon: "shopping_cart", isCurrency: false },
      { title: "Nombre des utilisateurs", value: countUsers, isIncrease: false, color: "primary", percentValue: "0.8361", icon: "portrait", isCurrency: false }
    ]);
  }

  constructor() { }
}
