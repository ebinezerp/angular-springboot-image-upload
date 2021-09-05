import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  constructor(private httpClient: HttpClient) { }

  upload(data:FormData): Observable<boolean> {
    return this.httpClient.post<boolean>('http://localhost:8080/server/file-upload',data);
  }

}
