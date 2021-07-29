import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';

// TODO: Replace this with your own data model type
export interface OrdersTableItem {
  texte: string;
  dateDebut: string;

  titre: string;
  id: number;
}

// TODO: replace this with real data from your application
const EXAMPLE_DATA: OrdersTableItem[] = [
  {id: 1, titre: 'Hydrogen', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 2, titre: 'Helium', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 3, titre: 'Lithium', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 4, titre: 'Beryllium', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 5, titre: 'Boron', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 6, titre: 'Carbon', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 7, titre: 'Nitrogen', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 8, titre: 'Oxygen', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 9, titre: 'Fluorine', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 10, titre: 'Neon', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 11, titre: 'Sodium', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 12, titre: 'Magnesium', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 13, titre: 'Aluminum', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 14, titre: 'Silicon', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 15, titre: 'Phosphorus', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 16, titre: 'Sulfur', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 17, titre: 'Chlorine', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 18, titre: 'Argon', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 19, titre: 'Potassium', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
  {id: 20, titre: 'Calcium', texte: 'Hydrogen', dateDebut: 'Hydrogen'},
];

/**
 * Data source for the OrdersTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class OrdersTableDataSource extends DataSource<OrdersTableItem> {
  data: OrdersTableItem[] = EXAMPLE_DATA;
  paginator: MatPaginator | undefined;
  sort: MatSort | undefined;

  constructor() {
    super();
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<OrdersTableItem[]> {
    if (this.paginator && this.sort) {
      // Combine everything that affects the rendered data into one update
      // stream for the data-table to consume.
      return merge(observableOf(this.data), this.paginator.page, this.sort.sortChange)
        .pipe(map(() => {
          return this.getPagedData(this.getSortedData([...this.data ]));
        }));
    } else {
      throw Error('Please set the paginator and sort on the data source before connecting.');
    }
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect(): void {}

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getPagedData(data: OrdersTableItem[]): OrdersTableItem[] {
    if (this.paginator) {
      const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
      return data.splice(startIndex, this.paginator.pageSize);
    } else {
      return data;
    }
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: OrdersTableItem[]): OrdersTableItem[] {
    if (!this.sort || !this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = this.sort?.direction === 'asc';
      switch (this.sort?.active) {
        case 'dateDebut': return compare(a.dateDebut, b.dateDebut, isAsc);
        case 'texte': return compare(a.texte, b.texte, isAsc);
        case 'titre': return compare(a.titre, b.titre, isAsc);
        case 'id': return compare(+a.id, +b.id, isAsc);
        default: return 0;
      }
    });
  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a: string | number, b: string | number, isAsc: boolean): number {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
