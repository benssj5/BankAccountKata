import { Component, OnInit } from '@angular/core';
import { CommonService } from "app/services/common.service";
import { Client } from "app/models/client";
import { ClientService } from "app/services/client.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.css']
})
export class ConnectionComponent implements OnInit{
  
  private client:Client;
  public email:string;
  private password:string;
  private errors:string;

  constructor(private clientService : ClientService,  private commonService :CommonService, private _router: Router) { 
    
  }

  ngOnInit() {

        this.commonService.selectClient.subscribe(client => this.client=client);

  }//fin ngOnINit


//connection du client
  connectClient(){
    this.client = new Client(1,"","","",null);
    this.client.email = this.email;
    this.client.password = this.password;

    this.clientService.connectClient(this.client)//on cherche le client par defaut dans la BD
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