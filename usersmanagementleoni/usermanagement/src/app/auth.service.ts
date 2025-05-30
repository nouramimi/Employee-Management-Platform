import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap , map , BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  //private apiUrl = '/api/auth'; // URL de l'API Spring Boot
  private baseUrl = '/api/auth';
  private currentUsername = new BehaviorSubject<string | null>(null);

      constructor(private http: HttpClient, private router: Router) { }
      
      register(user: any): Observable<any> {
        return this.http.post(`${this.baseUrl}/register`, user);
      }
      
      /*  login(username: string, password: string): Observable<{ token: string }> {
          return this.http.post<{ token: string }>(`${this.baseUrl}/login`, { username, password }).pipe(
            tap(response => {
              if (response.token) {
                localStorage.setItem('authToken', response.token);
                console.log("Token stored successfully");
              } else {
                console.error("No token received in the response");
              }
            })
          );
        }*/
         login(username: string, password: string): Observable<any> {
  return this.http.post<any>(`${this.baseUrl}/login`, { username, password }).pipe(
    map(response => {
      if (response.accessToken && response.tokenType) {
        const token = `${response.tokenType.trim()} ${response.accessToken.trim()}`; // Correctly format token
        localStorage.setItem('authToken', token);
        console.log("Token stored successfully");
        console.log(token);
        this.currentUsername.next(username);
      }
      return response;
    })
  );
}

getUsername(): Observable<string | null> {
  console.log(this.currentUsername);
  return this.currentUsername.asObservable();
}
          
          upload(files: File[]): Observable<any> {
            const formData = new FormData();
            files.forEach(file => formData.append('files', file, file.name));
            
            const token = this.getToken();
            if (!token) {
              console.error('No valid token found for upload');
              return new Observable(observer => observer.error('No valid token found'));
            }
          
            // Ensure only one 'Bearer' prefix
            const headers = new HttpHeaders({
              'Authorization': `Bearer ${token}`.trim()
            });
          
            return this.http.post<any>(`${this.baseUrl}/upload`, formData, { headers });
          }
          
          
          makeAuthenticatedRequest() {
            console.log('Calling makeAuthenticatedRequest');
            const token = this.getToken();
            console.log('Retrieved Token in makeAuthenticatedRequest:', token);
            const headers = new HttpHeaders({
              'Authorization': `Bearer ${token}`,
              'Content-Type': 'application/json' 
            });
        
            return this.http.get(`${this.baseUrl}/some-protected-route`, { headers });
          }
           
    
  logout(): void {
    localStorage.removeItem('authToken'); 
    this.router.navigate(['/login']); 
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('authToken'); 
  }

    getToken(): string | null {
      const token = localStorage.getItem('authToken');
      console.log('Retrieved Token:', token);
      if (token && token.startsWith('Bearer ')) {
        return token.substring(7).trim(); 
      }
      return token;
    }
    
    private getAuthHeaders(): HttpHeaders {
      const token = this.getToken();
            
            const headers = new HttpHeaders({
              'Authorization': `Bearer ${token}`.trim()
            });
      return headers;
    }
    getEmployees(): Observable<any> {
      return this.http.get('/api/auth/employees', { headers: this.getAuthHeaders() });
    }
  
    getEmployee(id: number): Observable<any> {
      return this.http.get(`/api/auth/employees/${id}`, { headers: this.getAuthHeaders() });
    }
  
    addEmployee(employee: any): Observable<any> {
      return this.http.post('/api/auth/employees', employee, { headers: this.getAuthHeaders() });
    }
  
    editEmployee(id: number, employee: any): Observable<any> {
      return this.http.put(`/api/auth/employees/${id}`, employee, { headers: this.getAuthHeaders() });
    }
  
    deleteEmployee(id: number): Observable<any> {
      return this.http.delete(`/api/auth/employees/${id}`, { headers: this.getAuthHeaders() });
    }


    
}
