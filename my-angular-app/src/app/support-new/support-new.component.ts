import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SupportRequest } from '../common/data/SupportRequest';
import { SupportResponse } from '../common/data/SupportResponse';
import { SessionService } from '../common/service/session.service';
import { SupportService } from '../common/service/support.service';

@Component({
  selector: 'app-support-new',
  templateUrl: './support-new.component.html',
  styleUrls: ['./support-new.component.scss']
})
export class SupportNewComponent implements OnInit {

  messageErr: string = "";
  supportRequest: SupportRequest = new SupportRequest()
  annonceId: number = 0;
  

  constructor(
    private supportService: SupportService,
    public sesssionService: SessionService,
    private router: Router,
    private route: ActivatedRoute) {
    this.annonceId = route.snapshot.params['annonceId'];
  }

  public RetourAnnonceSupport() {
    this.router.navigateByUrl("ngr-annonce-support/" + this.annonceId);
  }

  public ajoutSupport() {
    this.messageErr = "";
    this.supportRequest!.typeSupport = "Image";
    this.supportRequest!.annonceId = this.annonceId;

    this.supportService.creatSupport$(this.supportRequest)
      .subscribe({
        next: (s: SupportResponse) => {
          this.RetourAnnonceSupport();
        },
        error: (err) => {
          console.log("error:" + err);
          this.messageErr = "Erreur de création" + err
        }
      });
  }

  ngOnInit(): void {
  }

}
