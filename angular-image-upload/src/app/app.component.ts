import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FileUploadService } from './file-upload.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-image-upload';

  message='';
  filename: string;
  selectedFile?: File; 

  constructor(private fileUploadService: FileUploadService){
    this.filename='';
  }

  onSelect(event:any){
    this.selectedFile = event.target.files[0];
  }


  submit(){
    const formData = new FormData();
    formData.append("filename",this.filename);
    if(this.selectedFile){
      formData.append("image",this.selectedFile);
      this.fileUploadService.upload(formData).subscribe(
        (resp)=>{
          if(resp){
            this.message='Image uploaded succesfully';
          }else{
            this.message="Image upload failed"; 
          }
        }
      )
    }
  }

}
