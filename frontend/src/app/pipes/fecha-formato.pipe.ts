import { Pipe, PipeTransform } from '@angular/core';
import { formatDate } from '@angular/common';

@Pipe({
  name: 'fechaFormato',
  standalone: true
})
export class FechaFormatoPipe implements PipeTransform {

  transform(value: string | Date): string {
    if (!value) return '';

    const fecha = typeof value === 'string' ? new Date(value) : value;

    return formatDate(fecha, 'dd-MM-yyyy', 'en-US');
  }
}
