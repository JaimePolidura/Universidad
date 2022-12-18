import {Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-add-item-localstorage-modal',
  templateUrl: './input-name-modal.component.html',
  styleUrls: ['./input-name-modal.component.css']
})
export class InputNameModalComponent {
  @Input() name: string = '';
  @Input() defaultValue: string = '';
  @Input() isNumber: boolean = false;

  @Output() nameSelected = new EventEmitter<string>();

  @ViewChild('inputName') inputName: ElementRef<HTMLInputElement> | undefined;

  constructor(
    public activeModal: NgbActiveModal,
  ) { }

  onSelected(): void {
    const inputNameCasted = this.inputName as ElementRef<HTMLInputElement>;

    if(inputNameCasted.nativeElement.value == undefined || inputNameCasted.nativeElement.value == ''){
      window.alert("Introduce bien el nombre");
      return;
    }

    this.activeModal.close();
    this.nameSelected.next(inputNameCasted.nativeElement.value);
  }

}
