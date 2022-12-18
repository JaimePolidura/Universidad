import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ConfirmControlButtonsStyles} from "./confirm-control-buttons-styles";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-confirmation-modal',
  templateUrl: './confirmation-modal.component.html',
  styleUrls: ['./confirmation-modal.component.css']
})
export class ConfirmationModalComponent {
  @Input() title: string = 'Confirmar';
  @Input() body: string = '';
  @Input() list: string[] = [];
  @Input() styles: ConfirmControlButtonsStyles = ConfirmControlButtonsStyles.CANCEL_RED_OK_GREEN;
  @Input() okButtonText: string = "✓ Aceptar ✓";
  @Input() cancelButtonText: string = "✗ Cancelar ✗";
  @Input() closeAction: () => void = () => this.activeModal.close();

  @Output() onAccept: EventEmitter<void> = new EventEmitter<void>();

  constructor(private activeModal: NgbActiveModal) {}

  public onAcceptFn(): void {
    this.onAccept.emit();
    this.closeModal();
  }

  public closeModal(): void {
    this.closeAction.call(this);
  }
}
