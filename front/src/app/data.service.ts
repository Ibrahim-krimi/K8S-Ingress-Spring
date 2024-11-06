import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './environment';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private orderserviceAPI = environment.orderserviceAPI;
  private productserviceAPI = environment.productserviceAPI;
  private userserviceAPI = environment.userserviceAPI;


  constructor(private http: HttpClient) {}

  productservice(): Observable<string> {
    return this.http.get<string>(`${this.productserviceAPI}/productservice`);
  }

  orderservice(): Observable<string> {
    return this.http.get<string>(`${this.orderserviceAPI}/orderservice`);
  }

  userservice(): Observable<string> {
    return this.http.get<string>(`${this.userserviceAPI}/userservice`);
  }
}
