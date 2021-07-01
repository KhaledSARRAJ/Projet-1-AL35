import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnonceSimple } from '../common/data/annonceSimple';
import { AnnoncesService } from '../common/service/annonces.service';
import { SessionService } from '../common/service/session.service';

@Component({
  selector: 'app-annonce-liste-uti',
  templateUrl: './annonce-liste-uti.component.html',
  styleUrls: ['./annonce-liste-uti.component.scss']
})
export class AnnonceListeUtiComponent implements OnInit {

  messageErr: string = "";
  listeAnnoncesSimple!: AnnonceSimple[];

  constructor(private annoncesService: AnnoncesService,
    private sessionService: SessionService,
    private route: ActivatedRoute,
    private router: Router) { }

  public detailAnnonce(a: AnnonceSimple) {
    this.router.navigateByUrl("ngr-annonce-detail/" + a.id);
  }

  public supprimerAnnonce(a: AnnonceSimple) {
    this.messageErr = "";
    this.annoncesService.deleteAnnonce$(a.id)
      .subscribe({
        next: () => {
          this.messageErr = "Bien supprimer";
          this.initListe()
        },
        error: (err) => {
          console.log("error:" + err);
          this.messageErr = "Erreur de suppression " + err;
        }
      });
  }

  public ajoutAnnonce() {
    this.router.navigateByUrl("ngr-annonce-new");
  }

  public modifAnnonce(a: AnnonceSimple) {
    this.router.navigateByUrl("ngr-annonce-update/" + a.id);
  }

  public supportAnnonce(a: AnnonceSimple) {
    this.router.navigateByUrl("ngr-annonce-support/" + a.id);
  }

  public initListe() {
    this.annoncesService.getAnnonceByUti$(this.sessionService.utilisateurId)
      .subscribe({
        next: (tabAnnonces: AnnonceSimple[]) => {
          this.listeAnnoncesSimple = tabAnnonces;
        },
        error: (err) => {
          console.log("error:" + err);
          this.messageErr = "Erreur sur annonces" + err
        }
      });
  }

  ngOnInit(): void {
    this.initListe();
  }

}
