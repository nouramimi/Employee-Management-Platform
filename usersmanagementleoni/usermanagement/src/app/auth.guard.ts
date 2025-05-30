import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service'; // Assurez-vous que le chemin est correct

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      return true; // Autoriser l'accès si l'utilisateur est authentifié
    } else {
      this.router.navigate(['/login']); // Rediriger vers la page de connexion si non authentifié
      return false;
    }
  }

}
