import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateTime'
})
export class DateTimePipe implements PipeTransform {

  transform(value?: string, showTime: boolean = false): string {
    return value ?
      (showTime ?
        value.split("T")[0] + " " + value.split("T")[1].split(".")[0] :
        value.split("T")[0]) :
      '';
  }

}
