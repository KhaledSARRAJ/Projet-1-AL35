import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ChartsModule} from 'ng2-charts';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {NavComponent} from './nav/nav.component';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {DashComponent} from './dash/dash.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatMenuModule} from '@angular/material/menu';
import {CardComponent} from './card/card.component';
import {ProductSalesChartComponent} from './charts/product-sales-chart/product-sales-chart.component';
import {SalesTrafficChartComponent} from './charts/sales-traffic-chart/sales-traffic-chart.component';
import {AnnualSalesChartComponent} from './charts/annual-sales-chart/annual-sales-chart.component';
import {StoreSessionsChartComponent} from './charts/store-sessions-chart/store-sessions-chart.component';
import {HttpClientModule} from "@angular/common/http";
import {OrdersTableComponent} from './orders-table/orders-table.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MiniCardComponent} from './mini-card/mini-card.component';
import {PublicationTableComponent} from './publication-table/publication-table.component';
import {UtilisateurTableComponent} from './utilisateur-table/utilisateur-table.component';
import {SignalementTableComponent} from './signalement-table/signalement-table.component';
import {AnnonceTableComponent} from './annonce-table/annonce-table.component';
import {AcceuilComponent} from './acceuil/acceuil.component';
import {NgxSpinnerModule} from "ngx-spinner";
import { PublicationsDetailsComponent } from './publication-table/publications-details/publications-details.component';
import {FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    DashComponent,
    CardComponent,
    ProductSalesChartComponent,
    SalesTrafficChartComponent,
    AnnualSalesChartComponent,
    StoreSessionsChartComponent,
    OrdersTableComponent,
    MiniCardComponent,
    PublicationTableComponent,
    UtilisateurTableComponent,
    SignalementTableComponent,
    AnnonceTableComponent,
    AcceuilComponent,
    PublicationsDetailsComponent
  ],
  imports: [
    BrowserModule,
    ChartsModule,
    AppRoutingModule,
    NoopAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    NgxSpinnerModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
