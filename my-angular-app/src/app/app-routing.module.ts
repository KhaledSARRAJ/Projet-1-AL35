import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AnnonceDetailComponent } from './annonce-detail/annonce-detail.component';
import { AnnonceListeUtiComponent } from './annonce-liste-uti/annonce-liste-uti.component';
import { AnnonceListeComponent } from './annonce-liste/annonce-liste.component';
import { AnnonceNewComponent } from './annonce-new/annonce-new.component';
import { AnnonceSupportComponent } from './annonce-support/annonce-support.component';
import { AnnonceUpdateComponent } from './annonce-update/annonce-update.component';
import { SupportNewComponent } from './support-new/support-new.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  { path: 'ngr-welcome', component: WelcomeComponent },
  { path: '', redirectTo: '/ngr-welcome', pathMatch: 'full'},
  { path: 'ngr-annonce-liste/:utilisateurId', component: AnnonceListeComponent},
  { path: 'ngr-annonce-detail/:annonceId', component: AnnonceDetailComponent},
  { path: 'ngr-annonce-liste-uti', component: AnnonceListeUtiComponent},
  { path: 'ngr-annonce-new', component: AnnonceNewComponent},
  { path: 'ngr-annonce-update/:annonceId', component: AnnonceUpdateComponent},
  { path: 'ngr-annonce-support/:annonceId', component: AnnonceSupportComponent},
  { path: 'ngr-support-new/:annonceId', component: SupportNewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
