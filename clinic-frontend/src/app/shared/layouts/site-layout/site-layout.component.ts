import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../../service/storage/storage.servise";
import {Router} from "@angular/router";

@Component({
  selector: 'app-site-layout',
  templateUrl: './site-layout.component.html',
  styleUrls: ['./site-layout.component.css']
})
export class SiteLayoutComponent implements OnInit{

  isLoggedIn = false;

  constructor(private storageService:TokenService, private router: Router) {
  }

  check() {
    this.storageService.isAccessTokenExpired();
  }

  logout() {
    this.isLoggedIn = false;
    this.storageService.clean();
    this.router.navigateByUrl("/");
  }

  ngOnInit(): void {
    this.isLoggedIn = !this.storageService.isAccessTokenExpired();
  }
}
