import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnnonceRequest } from '../common/data/AnnonceRequest';
import { AnnonceResponse } from '../common/data/AnnonceResponse';
import { AnnoncesService } from '../common/service/annonces.service';
import { SessionService } from '../common/service/session.service';

@Component({
  selector: 'app-annonce-new',
  templateUrl: './annonce-new.component.html',
  styleUrls: ['./annonce-new.component.scss']
})
export class AnnonceNewComponent implements OnInit {

  messageErr: string = "";
  annonceRequest: AnnonceRequest = new AnnonceRequest()
  annonceResponse: AnnonceResponse | null = null;


  constructor(
    private annonceService: AnnoncesService,
    public sesssionService: SessionService,
    private router: Router) { }

    public ajoutAnnonce() {
      this.messageErr = "";
      this.annonceRequest!.userId = this.sesssionService.utilisateurId;

      this.annonceService.creatAnnonce$(this.annonceRequest!)
        .subscribe({
          next: (a: AnnonceResponse) => {
            this.annonceResponse = a;
            this.router.navigateByUrl("ngr-annonce-liste-uti");
          },
          error: (err) => {
            console.log("error:" + err);
            this.messageErr = "Erreur de création" + err
            this.annonceRequest?.titre
          }
        });
    }


  ngOnInit(): void {
  }

}
