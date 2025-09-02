import { Component, OnInit } from '@angular/core';
import { EstuadiantesService } from '../../services/estuadiantes.service';
import { Estudiante } from '../../../models/estudiantes.model';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';

@Component({
  selector: 'app-estudiantes',
  templateUrl: './estudiantes.component.html',
  styleUrl: './estudiantes.component.css'
})
export class EstudiantesComponent implements OnInit {
  estudiantes!: Array<Estudiante>;
  estudiantesDataSource!: MatTableDataSource<Estudiante>;
  displayedColumns: string[] = ['id', 'nombre', 'apellido', 'codigo', 'programaId', 'pagos'];  
  
  constructor(private estudiantesService: EstuadiantesService, private router: Router) { }

  ngOnInit(): void {
    this.estudiantesService.getAllEstudiantes().subscribe({
      next: data =>{
        this.estudiantes = data;
        this.estudiantesDataSource = new MatTableDataSource(this.estudiantes);
      },
      error: err => console.log(err)
    });
  }

  listarPagosDeEstudiante(estudiante: Estudiante): void{
    this.router.navigate([`/admin/estudiante-details/${estudiante.codigo}`]);
  }

}
