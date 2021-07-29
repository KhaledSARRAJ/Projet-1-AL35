import {Component, OnInit} from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import {StoreSummary} from "../mini-card/store-summary";
import {StoreSummaryService} from "../mini-card/store-summary.service";
import {UtilisateurService} from "../../services/utilisateur/utilisateur.service";
import {UtilsServiceService} from "../../services/utils/utils-service.service";
import {HttpErrorResponse} from "@angular/common/http";
import {DataserviceService} from "../../services/publications/dataservice.service";
import {AnnonceService} from "../../services/annonces/annonce.service";
import {SignalementService} from "../../services/signalement/signalement.service";

@Component({
  selector: 'app-dash',
  templateUrl: './dash.component.html',
  styleUrls: ['./dash.component.css']
})
export class DashComponent implements OnInit {
  countUsers = 0;
  countPubs = 0;
  countAnnonces = 0;
  countSignalement = 0;
  miniCardData: StoreSummary[];
  /** Based on the screen size, switch from standard to one column per row */
    // dashboard.component.js
  cardLayout = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({matches}) => {
      if (matches) {
        return {
          columns: 1,
          miniCard: {cols: 1, rows: 1},
          chart: {cols: 1, rows: 2},
          table: {cols: 1, rows: 4},
        };
      }

      return {
        columns: 4,
        miniCard: {cols: 1, rows: 1},
        chart: {cols: 2, rows: 2},
        table: {cols: 4, rows: 4},
      };
    })
  );

  constructor(private breakpointObserver: BreakpointObserver, private summaryService: StoreSummaryService,
              private utilsService: UtilsServiceService, private utilisateurService: UtilisateurService,
              private ds: DataserviceService, private annonceService: AnnonceService,
              private signalementService: SignalementService) {
  }

  ngOnInit() {
    this.utilsService.showSpinner();
    this.getCountSignalements();
    this.getCountPublications();
    this.getCountAnnonces();
    this.countUtilisateurs();
  }

  countUtilisateurs(){
    this.utilisateurService.getUtilisateurs().subscribe( users => {
      users.forEach( () => {
        this.countUsers++;
      });
      this.getStoreSummary(this.countUsers, this.countPubs, this.countAnnonces, this.countSignalement);
    }, (error: HttpErrorResponse) => {
      throw new Error(`Error lors de la récupération des Utilisateur : ${JSON.stringify(error)}`);
    }, () => {
      this.utilsService.hideSpinner().then();
    });
  }

  private getStoreSummary(countUsers, countPub, countAnnonces, countSignalements) {
    this.summaryService.getStoreSummary(countUsers, countPub, countAnnonces, countSignalements).subscribe({
      next:summaryData=>{
        this.miniCardData = summaryData;
      }
    });
  }

  private getCountPublications() {
    this.ds.getPublicationss().subscribe((pubs) => {
      pubs.forEach( () => {
        this.countPubs++;
      });
    });
  }

  private getCountAnnonces() {
    this.annonceService.getAnnonces().subscribe((annaoces) => {
      annaoces.forEach( () => {
        this.countAnnonces++;
      });
    });
  }

  private getCountSignalements() {
    this.signalementService.getSignalement().subscribe((signalements) => {
      signalements.forEach( () => {
        this.countSignalement++;
      });
    });
  }
}
