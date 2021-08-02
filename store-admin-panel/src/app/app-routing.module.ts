import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashComponent} from "./dash/dash.component";
import {PublicationTableComponent} from "./publication-table/publication-table.component";
import {UtilisateurTableComponent} from "./utilisateur-table/utilisateur-table.component";
import {SignalementTableComponent} from "./signalement-table/signalement-table.component";
import {AnnonceTableComponent} from "./annonce-table/annonce-table.component";
import {AcceuilComponent} from "./acceuil/acceuil.component";
import { PublicationsDetailsComponent } from './publication-table/publications-details/publications-details.component';

const routes: Routes = [{path: 'Synthèse', component: DashComponent},
  {path: 'Publication', component: PublicationTableComponent},
  {path: 'Utilisateur', component: UtilisateurTableComponent},
  {path: 'Signalement', component: SignalementTableComponent},
  {path: 'Annonce', component: AnnonceTableComponent},
  {path: 'publication-details', component: PublicationsDetailsComponent},
  {path: '', component: AcceuilComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
