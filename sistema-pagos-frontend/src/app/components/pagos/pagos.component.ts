import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { EstuadiantesService } from '../../services/estuadiantes.service';

@Component({
  selector: 'app-pagos',
  templateUrl: './pagos.component.html',
  styleUrl: './pagos.component.css'
})
export class PagosComponent implements OnInit {
  public pagos: any;
  public dataSource: any;
  public displayedColumns = ['id', 'fecha', 'cantidad', 'type', 'status', 'nombre'];

  /*
    @ViewChild: Decorador que permite acceder a un componente hijo, directiva o elemento DOM desde el componente padre.
  */
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private http: HttpClient, private estuadiantesService: EstuadiantesService) { }

  ngOnInit(): void {
    this.http.get("http://localhost:8080/pagos").subscribe({
      next: data => {
        this.pagos = data;
        this.dataSource = new MatTableDataSource(this.pagos);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: err => console.log(err)
    });
  }
}
