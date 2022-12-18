import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendRoutesService {
  private LOCALHOST: string = "http://localhost:7070";
  constructor() { }

  public USING: string = this.LOCALHOST;
}
