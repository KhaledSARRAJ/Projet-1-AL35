import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UtilsServiceService } from 'src/services/utils/utils-service.service';
import {DataserviceService} from "../../../services/publications/dataservice.service";

@Component({
  selector: 'app-publications-details',
  templateUrl: './publications-details.component.html',
  styleUrls: ['./publications-details.component.css']
})
export class PublicationsDetailsComponent implements OnInit {
  texte= '';
  constructor(private publicationService:DataserviceService,
              private utilsService: UtilsServiceService) { }
  ngOnInit(): void {
    this.utilsService.showSpinner();
    this.publicationService.getPublication(this.publicationService.idPublicationDetails).subscribe((event) => {
      console.log(JSON.stringify(event));
      this.texte = event.texte;
      console.log(this.texte);
    }, (error: HttpErrorResponse) => {
      throw new Error(`Error lors de la récupération cet Utilisateur : ${JSON.stringify(error)}`);
    }, () => {
      this.utilsService.hideSpinner().then();
    });
  }

}


