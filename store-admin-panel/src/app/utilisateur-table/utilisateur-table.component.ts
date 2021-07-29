import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {UtilisateurService} from "../../services/utilisateur/utilisateur.service";
import {Utilisateur} from "../../models/utilisateur/utilisateur";
import {UtilsServiceService} from "../../services/utils/utils-service.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-utilisateur-table',
  templateUrl: './utilisateur-table.component.html',
  styleUrls: ['./utilisateur-table.component.css']
})
export class UtilisateurTableComponent implements OnInit, AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<Utilisateur>;
  dataSource = new MatTableDataSource<Utilisateur>();

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'nom', 'prenom', 'mail', 'telephone', 'dateNais'];

  constructor(private utilisateurService: UtilisateurService,
              private utilsService: UtilsServiceService) {
  }

  ngOnInit(): void {
    this.utilsService.showSpinner();
    this.utilisateurService.getUtilisateurs().subscribe((users) => {
      this.dataSource.data = users;
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

}
