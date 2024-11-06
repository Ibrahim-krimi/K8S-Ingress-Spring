import { Component } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  data: string = '';

  constructor(private dataService: DataService) {}

  fetchData1() {
    this.dataService.productservice().subscribe(response => {
      this.data = response;
    });
  }

  fetchData2() {
    this.dataService.orderservice().subscribe(response => {
      this.data = response;
    });
  }

  fetchData3() {
    this.dataService.userservice().subscribe(response => {
      this.data = response;
    });
  }
}
