import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {PublicationTableDataSource, PublicationTableItem} from './publication-table-datasource';
import {DataserviceService} from "../../services/publications/dataservice.service";
import {UtilsServiceService} from "../../services/utils/utils-service.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Utilisateur} from "../../models/utilisateur/utilisateur";
import {Router} from '@angular/router';

@Component({
  selector: 'app-publication-table',
  templateUrl: './publication-table.component.html',
  styleUrls: ['./publication-table.component.css']
})
export class PublicationTableComponent implements OnInit, AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<PublicationTableItem>;
  dataSource = new MatTableDataSource<PublicationTableItem>();

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'titre', 'nbSignalement', 'dateDebut', 'action'];

  constructor(private ds: DataserviceService,
              private router: Router,
              private utilsService: UtilsServiceService) {
  }

  ngOnInit(): void {
    this.utilsService.showSpinner();
    this.ds.getPublicationss().subscribe(( publications) => {
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
  publicationDetails(id) {
    this.router.navigateByUrl(`/publication-details`);
    this.ds.idPublicationDetails = id;
  }
  publicationsDelete(id, row: any) {
    this.utilsService.showSpinner();
    const index = this.dataSource.data.indexOf(row, 0);
    if (index > -1) {
      this.dataSource.data.splice(index, 1);
    }
    setTimeout(() => {
      this.dataSource.paginator = this.paginator;
    });
    this.table.renderRows();
    this.ds.deletePublication(id).subscribe(data => {
      this.dataSource.paginator = this.paginator;
    }, (error: HttpErrorResponse) => {
      throw new Error(`Error lors de la suppression de cette publication : ${JSON.stringify(error)}`);
    }, () => {
      this.utilsService.hideSpinner().then();
    });
  }
}
