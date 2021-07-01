import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { AnnonceResponse } from '../common/data/AnnonceResponse';
import { AnnoncesService } from '../common/service/annonces.service';

@Component({
  selector: 'app-annonce-detail',
  templateUrl: './annonce-detail.component.html',
  styleUrls: ['./annonce-detail.component.scss']
})
export class AnnonceDetailComponent implements OnInit {

  message = "";
  annonceId: number = 0;
  annonceResponse: AnnonceResponse | null = null;

  constructor(private annoncesService: AnnoncesService,
    private _location: Location,
    private route: ActivatedRoute) {
    this.annonceId = route.snapshot.params['annonceId']
  }

  public goBack(){
    this._location.back();
  }

  public initinfo() {
    this.annonceResponse = null;
    this.annoncesService.getAnnonceById$(this.annonceId).subscribe(
      {
        next: (annonce) => { this.annonceResponse = annonce },
        error: (error) => { console.log(error); this.message = "erreur: " + error.message }
      });
  }

  ngOnInit(): void {
    this.initinfo()
  }
}

