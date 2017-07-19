import { Component, OnInit } from '@angular/core';
import { CommonService } from "app/services/common.service";
import { Client } from "app/models/client";
import { Compte } from "app/models/compte";
import { ClientService } from "app/services/client.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit{
  
  private client:Client;

  constructor(private clientService : ClientService, private commonService :CommonService, private _router: Router) { 
    
  }

  ngOnInit() {
        let compte:Compte = new Compte("",null,0,0,null);

        this.commonService.selectClient.subscribe(client => this.client=client);
        
        this.client = new Client(1,"",'','',compte);
 console.log("password  1: " + this.client.password);
  }//fin ngOnINit



insertClient() {
  console.log("password : " + this.client.password);

      this.clientService.saveClient(this.client)//on cherche le client par defaut dans la BD
        .subscribe( client => { this.client = client;
                                this.commonService.selectClient.next(this.client);//indispensable, on met la nouvelle valeur a partager
                                console.log("client : " + this.client.nom);
                                //pour faire une redirection automatique
                                let link=['/client'];
                                this._router.navigate(link);  
    
                                }, 
                    e => console.log(e.message));

}

}