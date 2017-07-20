import { Component, OnInit } from '@angular/core';
import { CommonService } from "app/services/common.service";
import { Client } from "app/models/client";
import { Router } from "@angular/router";

@Component({
  selector: 'app-disconnection',
  templateUrl: './disconnection.component.html',
  styleUrls: ['./disconnection.component.css']
})
export class DisconnectionComponent implements OnInit{
  
  private client:Client;

  constructor( private commonService :CommonService, private _router: Router) { 
    
  }

  ngOnInit() {

        this.commonService.selectClient.subscribe(client => this.client=client);

        //on deconnecte
        this.commonService.selectClient.next(null);

        //automatic redirection 
        // let link=['/client'];
        // this._router.navigate(link);  

  }//fin ngOnINit

}