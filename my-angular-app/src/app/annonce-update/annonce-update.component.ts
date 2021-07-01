import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnonceRequest } from '../common/data/AnnonceRequest';
import { AnnonceResponse } from '../common/data/AnnonceResponse';
import { AnnoncesService } from '../common/service/annonces.service';
import { SessionService } from '../common/service/session.service';

@Component({
  selector: 'app-annonce-update',
  templateUrl: './annonce-update.component.html',
  styleUrls: ['./annonce-update.component.scss']
})
export class AnnonceUpdateComponent implements OnInit {

  message = "";
  annonceId: number = 0;
  annonceResponse: AnnonceResponse = new AnnonceResponse();
  annonceRequest: AnnonceRequest = new AnnonceRequest();

  constructor(
    private annoncesService: AnnoncesService,
    private sessionService: SessionService,
    private router: Router,
    private route: ActivatedRoute) {
    this.annonceId = route.snapshot.params['annonceId']
  }


  public initinfo() {
    this.annoncesService.getAnnonceById$(this.annonceId)
      .subscribe({
        next: (annonce) => { this.annonceResponse = annonce },
        error: (error) => { console.log(error); this.message = "erreur: " + error.message }
      });
  }

  public modifierAnnonce() {
    this.message = "";

    this.annonceRequest.id = this.annonceResponse.id
    this.annonceRequest!.titre = this.annonceResponse.titre
    this.annonceRequest!.texte = this.annonceResponse.texte
    this.annonceRequest!.prix = this.annonceResponse.prix

    this.annonceRequest!.userId = this.sessionService.utilisateurId;

    this.annoncesService.majAnnonce$(this.annonceRequest!)
      .subscribe({
        next: (a: AnnonceResponse) => {
          this.annonceResponse = a;
          this.router.navigateByUrl("ngr-annonce-liste-uti");
        },
        error: (err) => {
          console.log("error:" + err);
          this.message = "Erreur de création" + err
        }
      });

  }

  ngOnInit(): void {
    this.initinfo()
  }

}
