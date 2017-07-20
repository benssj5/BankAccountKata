import { Component, OnInit } from '@angular/core';
import { CommonService } from "app/services/common.service";
import { Client } from "app/models/client";
import { Account } from "app/models/account";
import { ClientService } from "app/services/client.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit{
  
  private client:Client;
  private errors:string;

  constructor(private clientService : ClientService, private commonService :CommonService, private _router: Router) { 
    
  }

  ngOnInit() {
        let compte:Account = new Account("",null,0,0,null);

        this.commonService.selectClient.subscribe(client => this.client=client);
        
        this.client = new Client(1,"",'','',compte);
  }//fin ngOnINit



  insertClient() {

      this.clientService.saveClient(this.client)
        .subscribe( client => { this.client = client;
                                this.commonService.selectClient.next(this.client);
                                //automatic redirection 
                                let link=['/client'];
                                this._router.navigate(link);  
    
                                }, 
                    e => {
                          //console.log(e.message)
                          this.errors = "error during creation client";
                      });
                    

}

}