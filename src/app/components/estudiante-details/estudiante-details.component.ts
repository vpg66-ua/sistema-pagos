import { Component, OnInit } from '@angular/core';
import { Pago } from '../../../models/estudiantes.model';
import { MatTableDataSource } from '@angular/material/table';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { EstuadiantesService } from '../../services/estuadiantes.service';

@Component({
  selector: 'app-estudiante-details',
  templateUrl: './estudiante-details.component.html',
  styleUrl: './estudiante-details.component.css'
})
export class EstudianteDetailsComponent implements OnInit {
  estudianteCodigo!: string;
  pagosEstudiante!: Array<Pago>;
  pagosDataSource!: MatTableDataSource<Pago>;
  displayedColumns: string[] = ['id', 'fecha', 'cantidad', 'type', 'status', 'nombreEstudiante'];

  constructor(private estudiantesService: EstuadiantesService, private activatedRoute: ActivatedRoute, private router: Router) { }
  
  ngOnInit(): void {
    this.estudianteCodigo = this.activatedRoute.snapshot.params['codigo'];
    this.estudiantesService.getPagosDeEstudiante(this.estudianteCodigo).subscribe({
      next: data =>{
        this.pagosEstudiante = data;
        this.pagosDataSource = new MatTableDataSource(this.pagosEstudiante);
      },
      error: err => console.log(err)
    });
  }

  agregarPago() {
    this.router.navigateByUrl(`/admin/new-pago/${this.estudianteCodigo}`);
  }

}
