import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root'
})
export class LanguageService {

  constructor(private translate: TranslateService) {
    // Set the default language
    //this.translate.setDefaultLang('en');
  }

  // Method to change the language
  /*changeLanguage(language: string) {
    this.translate.use(language);
  }

  // Method to get the current language
  getCurrentLanguage(): string {
    return this.translate.currentLang;
  }*/
}
