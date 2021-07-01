import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule , HTTP_INTERCEPTORS} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MyAuthInterceptor } from './common/interceptor/my-auth.interceptor';
import { AppComponent } from './app.component';
import { MyHeaderComponent } from './my-header/my-header.component';
import { MyFooterComponent } from './my-footer/my-footer.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { AnnonceListeComponent } from './annonce-liste/annonce-liste.component';
import { AnnonceDetailComponent } from './annonce-detail/annonce-detail.component';
import { AnnonceListeUtiComponent } from './annonce-liste-uti/annonce-liste-uti.component';
import { AnnonceNewComponent } from './annonce-new/annonce-new.component';
import { AnnonceUpdateComponent } from './annonce-update/annonce-update.component';
import { AnnonceSupportComponent } from './annonce-support/annonce-support.component';
import { SupportNewComponent } from './support-new/support-new.component';

@NgModule({
  declarations: [
    AppComponent,
    MyHeaderComponent,
    MyFooterComponent,
    WelcomeComponent,
    AnnonceListeComponent,
    AnnonceDetailComponent,
    AnnonceListeUtiComponent,
    AnnonceNewComponent,
    AnnonceUpdateComponent,
    AnnonceSupportComponent,
    SupportNewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    TabsModule.forRoot()
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MyAuthInterceptor,
      multi: true
      }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
