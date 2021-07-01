import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnonceSimple } from '../common/data/annonceSimple';
import { AnnoncesService } from '../common/service/annonces.service';
import { SessionService } from '../common/service/session.service';



@Component({
  selector: 'app-annonce-liste',
  templateUrl: './annonce-liste.component.html',
  styleUrls: ['./annonce-liste.component.scss']
})
export class AnnonceListeComponent implements OnInit {

  utiId: number = 0;
  messageErr: string = "";
  listeAnnoncesSimple!: AnnonceSimple[];

  constructor(private annoncesService: AnnoncesService,
    public sessionService: SessionService,
    private route: ActivatedRoute,
    private router: Router) { 
      this.utiId = route.snapshot.params['utilisateurId']
    }
  

  public detailAnnonce(a: AnnonceSimple) {
    this.router.navigateByUrl("ngr-annonce-detail/" + a.id);
  }

  public initListe() {
    this.annoncesService.getAllAnnoncesSimple$()
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
    this.initListe ();
    this.sessionService.utilisateurId = this.utiId;
  }

}
