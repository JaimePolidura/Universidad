import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateTime'
})
export class DateTimePipe implements PipeTransform {

  transform(value?: string): string {
    return value ? value.split("T")[0] : '';
  }

}
