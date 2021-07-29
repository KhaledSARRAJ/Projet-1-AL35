import { Injectable } from '@angular/core';
import {NgxSpinnerService} from "ngx-spinner";

@Injectable({
  providedIn: 'root'
})
export class UtilsServiceService {

  constructor(private spinner: NgxSpinnerService) { }

  hideSpinner() {
    return this.spinner.hide();
  }

  showSpinner() {
    this.spinner.show(undefined, {
      fullScreen: true,
      type: 'ball-spin-clockwise',
      size: 'medium',
      bdColor: 'rgba(0,0,0,0)',
      color: 'rgb(50,0,14)'
    }).then();
  }
}
