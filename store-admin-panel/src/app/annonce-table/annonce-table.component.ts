import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {AnnonceTableDataSource} from './annonce-table-datasource';
import {Annonces} from "../../models/annonces/annonces";
import {AnnonceService} from "../../services/annonces/annonce.service";
import {HttpErrorResponse} from "@angular/common/http";
import {DataserviceService} from "../../services/publications/dataservice.service";
import {UtilsServiceService} from "../../services/utils/utils-service.service";
import {PublicationTableItem} from "../publication-table/publication-table-datasource";

@Component({
  selector: 'app-annonce-table',
  templateUrl: './annonce-table.component.html',
  styleUrls: ['./annonce-table.component.css']
})
export class AnnonceTableComponent implements OnInit, AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<Annonces>;
  dataSource = new MatTableDataSource<Annonces>();

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'titre', 'texte', 'prix', 'dateParution', 'action'];

  constructor(private annonceService: AnnonceService,
              private utilsService: UtilsServiceService) {
  }

  ngOnInit(): void {
    this.utilsService.showSpinner();
    this.annonceService.getAnnonces().subscribe(( publications) => {
      this.dataSource.data = publications;
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.table.dataSource = this.dataSource;
    }, (error: HttpErrorResponse) => {
      throw new Error(`Error lors de la récupération des Utilisateurs : ${JSON.stringify(error)}`);
    }, () => {
      this.utilsService.hideSpinner().then();
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  deleteAnnonce(id, row: any) {
    this.utilsService.showSpinner();
    const index = this.dataSource.data.indexOf(row, 0);
    if (index > -1) {
      this.dataSource.data.splice(index, 1);
    }
    setTimeout(() => {
      this.dataSource.paginator = this.paginator;
    });
    this.table.renderRows();
    this.annonceService.deleteAnnonce(id).subscribe(data => {
      this.dataSource.paginator = this.paginator;
    }, (error: HttpErrorResponse) => {
      throw new Error(`Error lors de la suppression de cette publication : ${JSON.stringify(error)}`);
    }, () => {
      this.utilsService.hideSpinner().then();
    });
  }
}
