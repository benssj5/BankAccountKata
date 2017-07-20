import { Component, OnInit } from '@angular/core';
import { ClientService } from "app/services/client.service";
import { Client } from "app/models/client";
import { CommonService } from "app/services/common.service";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit{
  
    private client:Client;

  constructor(private clientService : ClientService, private commonService :CommonService) { }

  ngOnInit() {

    
    this.commonService.selectClient.subscribe(
          client => this.client=client);    

  }//fin ngOnINit


}
