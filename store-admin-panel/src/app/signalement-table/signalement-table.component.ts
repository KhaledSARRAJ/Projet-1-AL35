import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {UtilsServiceService} from "../../services/utils/utils-service.service";
import {HttpErrorResponse} from "@angular/common/http";
import {SignalementService} from "../../services/signalement/signalement.service";
import {Signalement} from "../../models/signalement/signalement";

@Component({
  selector: 'app-signalement-table',
  templateUrl: './signalement-table.component.html',
  styleUrls: ['./signalement-table.component.css']
})
export class SignalementTableComponent implements OnInit, AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<Signalement>;
  dataSource = new MatTableDataSource<Signalement>();

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'dateSignalement', 'type', 'identifiant', 'userid', 'libelleMotifSignalement', 'action'];

  constructor(private signalementService: SignalementService,
              private utilsService: UtilsServiceService) {
  }

  ngOnInit(): void {
    this.utilsService.showSpinner();
    this.signalementService.getSignalement().subscribe((signalements) => {
      this.dataSource.data = signalements;
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.table.dataSource = this.dataSource;
    }, (error: HttpErrorResponse) => {
      throw new Error(`Error lors de la récupération des Signalements : ${JSON.stringify(error)}`);
    }, () => {
      this.utilsService.hideSpinner().then();
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  deleteSignalement(id, row: any) {
    this.utilsService.showSpinner();
    const index = this.dataSource.data.indexOf(row, 0);
    if (index > -1) {
      this.dataSource.data.splice(index, 1);
    }
    setTimeout(() => {
      this.dataSource.paginator = this.paginator;
    });
    this.table.renderRows();
    this.signalementService.deleteSignalement(id).subscribe(data => {
      this.dataSource.paginator = this.paginator;
    }, (error: HttpErrorResponse) => {
      throw new Error(`Error lors de la suppression de ce signalement : ${JSON.stringify(error)}`);
    }, () => {
      this.utilsService.hideSpinner().then();
    });
  }
}
