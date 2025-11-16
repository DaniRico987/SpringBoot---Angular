import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected userName: string = 'Daniel Rico';

  constructor() {}

  changeUserName(name: string) {
    this.userName = name;
  }
}
