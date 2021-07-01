import { Component, OnInit } from '@angular/core';
import { SessionService } from '../common/service/session.service';

@Component({
  selector: 'app-my-header',
  templateUrl: './my-header.component.html',
  styleUrls: ['./my-header.component.scss']
})
export class MyHeaderComponent implements OnInit {

   constructor(public sessionService: SessionService) { }

  public ListeAnnonceUt() {
  }

  ngOnInit(): void {
  }

}
