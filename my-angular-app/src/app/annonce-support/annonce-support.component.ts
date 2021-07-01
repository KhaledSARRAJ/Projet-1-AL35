import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnonceResponse } from '../common/data/AnnonceResponse';
import { SupportRequest } from '../common/data/SupportRequest';
import { SupportResponse } from '../common/data/SupportResponse';
import { AnnoncesService } from '../common/service/annonces.service';
import { SupportService } from '../common/service/support.service';

@Component({
  selector: 'app-annonce-support',
  templateUrl: './annonce-support.component.html',
  styleUrls: ['./annonce-support.component.scss']
})
export class AnnonceSupportComponent implements OnInit {

  message = "";
  annonceId: number = 0;
  annonceResponse: AnnonceResponse | null = null;
  supportRequest: SupportRequest = new SupportRequest();
  supportResponse: SupportResponse = new SupportResponse();

  constructor(
    private annoncesService: AnnoncesService,
    private supportService: SupportService,
    private router: Router,
    private route: ActivatedRoute) {
    this.annonceId = route.snapshot.params['annonceId']
  }

  public ajoutSupport() {
    this.router.navigateByUrl("ngr-support-new/" + this.annonceId);
  }

  public supprimerSupport(supportId: number) {
    this.message = "";

    // info du support
    this.supportService.getSupportById$(supportId).subscribe(
      {
        next: (support) => {
          this.supportResponse = support;
          // maj support pour couper le lien avec annonce
          this.majSupport(supportId)
        },
        error: (error) => { console.log(error); this.message = "erreur: " + error.message }
      });
  }

  public majSupport(supportId: number) {

    this.supportRequest.id = this.supportResponse?.id;
    this.supportRequest.chemin = this.supportResponse?.chemin;
    this.supportRequest.typeSupport = this.supportResponse?.typeSupport;
    this.supportRequest.image = this.supportResponse?.image;
    this.supportRequest.annonceId = null;

    this.supportService.updateSupport$(this.supportRequest)
      .subscribe({
        next: (s: SupportResponse) => {
          // suppression support
          this.deleteSupport(supportId);
        },
        error: (err) => {
          console.log("error:" + err);
          this.message = "Erreur de création" + err
        }
      });
  }


  public deleteSupport(supportId: number) {
    this.supportService.deleteSupport$(supportId)
      .subscribe({
        next: () => {
          // initialisation de la page
          this.initInfo()
        },
        error: (err) => {
          console.log("error:" + err);
          this.message = "Erreur de suppression " + err;
        }
      });
  }


  public initInfo() {
    this.annonceResponse = null;
    this.annoncesService.getAnnonceById$(this.annonceId).subscribe(
      {
        next: (annonce) => { this.annonceResponse = annonce },
        error: (error) => { console.log(error); this.message = "erreur: " + error.message }
      });
  }

  ngOnInit(): void {
    this.initInfo()
  }

}
