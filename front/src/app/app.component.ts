import { Component } from '@angular/core';
import { DashboardComponent } from './dashboard/dashboard.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] // Correction ici : c'est `styleUrls` et non `styleUrl`
})
export class AppComponent {
  title = 'front';
}
