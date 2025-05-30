import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upload',
  //standalone: true,
  //imports: [],
  templateUrl: './upload.component.html',
  styleUrl: './upload.component.css'
})
export class UploadComponent {
  constructor(private authService: AuthService, private router: Router) {}
  
  validateFile(file: File): boolean {
    const allowedExtensions = ['xlsx'];
    const fileExtension = file.name.split('.').pop() || '';
    return allowedExtensions.includes(fileExtension);
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) {
      return;
    }

    const files = input.files;
    const fileArray = Array.from(files);

    for (const file of fileArray) {
      if (!this.validateFile(file)) {
        alert('Invalid file type. Only .xlsx files are allowed.');
        return;
      }
    }

    this.uploadFiles(fileArray);
  }
  uploadFiles(files: File[]): void {
    this.authService.upload(files).subscribe(
      response => console.log('Upload successful', response),
      error => console.error('Upload error', error)
    );
  }
  
}
