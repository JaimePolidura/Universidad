import {AfterViewInit, Directive, ElementRef, Input} from '@angular/core';

@Directive({
  selector: '[appFocus]'
})
export class FocusDirective implements AfterViewInit{
  @Input() appFocus: string | undefined;
  @Input() appFocusDoFocus: boolean = true;

  constructor(
    private element: ElementRef<HTMLInputElement>
  ) { }

  ngAfterViewInit(): void {
    if(this.appFocusDoFocus)
      this.element.nativeElement.focus({preventScroll: true});
  }

}
