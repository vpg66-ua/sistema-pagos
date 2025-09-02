import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { TypePago } from '../../../models/estudiantes.model';
import { EstuadiantesService } from '../../services/estuadiantes.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-pago',
  templateUrl: './new-pago.component.html',
  styleUrl: './new-pago.component.css'
})
export class NewPagoComponent implements OnInit {
  pagoFormGroup!: FormGroup;
  codigoEstudiante!: string;
  
  tiposPago: string[] = [];
  pdfFileUrl!: string;

  constructor(private formBuilder: FormBuilder, private activatedRoute: ActivatedRoute, private estudiantesService: EstuadiantesService) { }
  
  ngOnInit(): void {
    for(let type in TypePago){
      let value = TypePago[type];
      if(typeof value == 'string'){
        this.tiposPago.push(value);
      }
    }

    this.codigoEstudiante = this.activatedRoute.snapshot.params['codigo'];
    this.pagoFormGroup = this.formBuilder.group({
      fecha: this.formBuilder.control(''),
      cantidad: this.formBuilder.control(''),
      type: this.formBuilder.control(''),
      status: this.formBuilder.control(''),
      codigoEstudiante: this.formBuilder.control(this.codigoEstudiante),
      fileName: this.formBuilder.control('')
    })
  }

  selectFile(event: any){
    //event.target representa el elemento HTML que ha lanzado el evento
    if(event.target.files.length > 0){
      const file = event.target.files[0];
      //patchValue actualiza elementos especificos del formulario
      this.pagoFormGroup.patchValue({
        fileSource: file,
        fileName: file.name
      });
      this.pdfFileUrl = window.URL.createObjectURL(file);
    } else {
      this.pagoFormGroup.patchValue({
        fileName: ''
      });
    }
  }

  guardarPago(){
    let date: Date = new Date(this.pagoFormGroup.value.fecha);
    
    let formattedDate = date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear(); // Formato: DD/MM/YYYY
    let formData = new FormData();

    formData.set('fecha', formattedDate);
    formData.set('cantidad', this.pagoFormGroup.value.cantidad);
    formData.set('type', this.pagoFormGroup.value.type);
    formData.set('codigoEstudiante', this.pagoFormGroup.value.codigoEstudiante);
    formData.set('file', this.pagoFormGroup.value.fileSource);

    console.log(formData);
    this.estudiantesService.guardarPago(formData).subscribe({
      next: (pago) => {
        Swal.fire({
          title: 'Pago guardado',
          text: `El pago de ${pago.cantidad} ha sido guardado correctamente`,
          icon: 'success'
        });
      },
      error: (err) => {
        Swal.fire({
          title: 'Error al guardar el pago',
          text: `Ha ocurrido un error al guardar el pago: ${err.message}`,
          icon: 'error'
        });
      }
    });
  }
}
