import { Client } from "app/models/client";

export class Account {

    codeAccount: string;
    dateCreation: Date;
    amount: number;
    overdraft: number;
    client: Client;

    constructor( codeAccount: string='',
    dateCreation: Date = new Date(),
    amount: number=1000,
    overdraft: number=200,
    client: Client=new Client() ){ 
        this.codeAccount = codeAccount,
        this.dateCreation = dateCreation,
        this.amount = amount,
        this.overdraft = overdraft,
        this.client = client
     } 
} 